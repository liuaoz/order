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
import java.util.ArrayList;
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
    public boolean pay(OrderDto orderDto) {
        return false;
    }

}
