package com.liuaoz.order.service;

import com.liuaoz.order.common.OrderException;
import com.liuaoz.order.common.OrderStatusEnum;
import com.liuaoz.order.common.PayStatusEnum;
import com.liuaoz.order.common.converter.OrderMaster2OrderDtoConverter;
import com.liuaoz.order.core.KeyUtil;
import com.liuaoz.order.core.RespCode;
import com.liuaoz.order.dto.CartDto;
import com.liuaoz.order.dto.OrderDto;
import com.liuaoz.order.model.OrderDetail;
import com.liuaoz.order.model.OrderMaster;
import com.liuaoz.order.model.Product;
import com.liuaoz.order.repository.OrderDetailRepository;
import com.liuaoz.order.repository.OrderMasterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description : TODO
 * @Author : matrix
 * @Date : created in 2018/1/6 21:42
 */
@Service
@Slf4j
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private IProductService iProductService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    @Transactional
    public OrderDto create(OrderDto orderDto) {

        BigDecimal amount = BigDecimal.ZERO;
        String orderId = KeyUtil.generateUniqueKey();

        //查询产品信息
        List<OrderDetail> orderDetails = orderDto.getOrderDetails();

        for (OrderDetail orderDetail : orderDetails) {

            Product product = iProductService.findOne(orderDetail.getProductId());

            //判断产品是否存在
            if (null == product) {
                throw new OrderException(RespCode.PRODUCT_NOT_EXIST);
            }

            amount = amount.add(product.getPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())));

            //订单详情入库
            orderDetail.setOrderId(orderId);
            orderDetail.setId(KeyUtil.generateUniqueKey());
            orderDetail.setProductName(product.getName());
            orderDetail.setProductPrice(product.getPrice());
            orderDetailRepository.save(orderDetail);

        }

        //写入订单信息
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto, orderMaster);
        orderMaster.setId(orderId);
        orderMaster.setAmount(amount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getStatus());
        orderMaster.setPayStatus(PayStatusEnum.UN_PAY.getStatus());
        orderMasterRepository.save(orderMaster);

        //减库存
        List<CartDto> cartList =
                orderDetails.stream().map(e -> new CartDto(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        iProductService.decreaseStock(cartList);

        //判断库存是否足够
        for (OrderDetail orderDetail : orderDetails) {
            if (orderDetail.getProductQuantity() > 11) {
                throw new OrderException(RespCode.PRODUCT_NOT_ENOUGH);
            }
        }

        return orderDto;
    }

    @Override
    public OrderDto findOne(String orderId) {
        OrderMaster ordermaster = orderMasterRepository.findOne(orderId);
        if (null == ordermaster) {
            throw new OrderException(RespCode.ORDER_NOT_EXIST);
        }

        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(orderId);
        if (null == orderDetails) {
            throw new OrderException(RespCode.ORDER_DETAIL_NOT_EXIST);
        }
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(ordermaster, orderDto);
        orderDto.setOrderDetails(orderDetails);
        return orderDto;
    }

    @Override
    public Page<OrderDto> findList(String buyerOpenid, Pageable pageable) {

        Page<OrderMaster> pageList = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);

        List<OrderDto> orderDtos
                = OrderMaster2OrderDtoConverter.convert(pageList.getContent());


        return new PageImpl<>(orderDtos, pageable, pageList.getTotalElements());

    }

    @Override
    @Transactional
    public OrderDto cancel(OrderDto orderDto) {

        OrderMaster master = orderMasterRepository.findOne(orderDto.getId());

        if (null == master) {
            throw new OrderException(RespCode.ORDER_NOT_EXIST);
        }

        if (!OrderStatusEnum.NEW.getStatus().equals(master.getOrderStatus())) {
            //若客户端请求的订单状态不是取消，则不能取消订单
            throw new OrderException(RespCode.ORDER_STATUS_ERROR);
        }

        master.setOrderStatus(OrderStatusEnum.CANCEL.getStatus());

        //修改订单
        orderMasterRepository.save(master);

        //返回库存
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(orderDto.getId());
        if (null == orderDetails) {
            throw new OrderException(RespCode.ORDER_DETAIL_NOT_EXIST);
        }
        List<CartDto> cartDtos = orderDetails.stream().map(e -> new CartDto(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        iProductService.increaseStock(cartDtos);

        //如果已支付退款
        if (PayStatusEnum.PAYED.getStatus().equals(master.getPayStatus())) {
            //TODO
            log.warn("取消订单，退款功能待实现...");
        }

        return OrderMaster2OrderDtoConverter.convert(master);
    }

    @Override
    public OrderDto finish(OrderDto orderDto) {

        OrderMaster orderMaster = orderMasterRepository.findOne(orderDto.getId());

        if (null == orderMaster) {
            throw new OrderException(RespCode.ORDER_NOT_EXIST);
        }

        if (!OrderStatusEnum.NEW.getStatus().equals(orderMaster.getOrderStatus())) {
            throw new OrderException(RespCode.ORDER_STATUS_ERROR);
        }

        orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getStatus());

        orderMasterRepository.save(orderMaster);

        return OrderMaster2OrderDtoConverter.convert(orderMaster);
    }

    @Override
    public OrderDto pay(OrderDto orderDto) {

        OrderMaster orderMaster = orderMasterRepository.findOne(orderDto.getId());

        if (null == orderMaster) {
            throw new OrderException(RespCode.ORDER_NOT_EXIST);
        }

        if (!OrderStatusEnum.NEW.getStatus().equals(orderMaster.getOrderStatus())) {
            throw new OrderException(RespCode.ORDER_STATUS_ERROR);
        }

        if(!PayStatusEnum.UN_PAY.getStatus().equals(orderMaster.getPayStatus())){
            throw new OrderException(RespCode.ORDER_PAY_STATUS_ERROR);
        }

        orderMaster.setPayStatus(PayStatusEnum.PAYED.getStatus());

        orderMasterRepository.save(orderMaster);

        return OrderMaster2OrderDtoConverter.convert(orderMaster);
    }

}
