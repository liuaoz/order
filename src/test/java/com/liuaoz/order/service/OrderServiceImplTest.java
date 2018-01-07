package com.liuaoz.order.service;

import com.liuaoz.order.BaseTest;
import com.liuaoz.order.common.OrderStatusEnum;
import com.liuaoz.order.dto.OrderDto;
import com.liuaoz.order.model.OrderDetail;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Description : TODO
 * @Author : matrix
 * @Date : created in 2018/1/6 23:05
 */
@Slf4j
public class OrderServiceImplTest extends BaseTest {

    @Autowired
    private IOrderService iOrderService;

    @Test
    public void create() throws Exception {

        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName("李四");
        orderDto.setBuyerOpenid("111111111111");
        orderDto.setBuyerPhone("18800001111");

        List<OrderDetail> orderDetails = new ArrayList<>();

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("123412341234");
        orderDetail.setProductQuantity(2);
        orderDetails.add(orderDetail);

        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setProductId("123412341235");
        orderDetail2.setProductQuantity(3);
        orderDetails.add(orderDetail2);

        orderDto.setOrderDetails(orderDetails);
        iOrderService.create(orderDto);

    }

    @Test
    public void testFindOrderById() {

        String orderId = "151525197329459611";

        OrderDto orderDto = iOrderService.findOne(orderId);

        log.info("订单信息：{}", orderDto.toString());

    }

    @Test
    public void testFindList() {
        String buyerOpenid = "111111111111";
        Pageable pageable = new PageRequest(0, 10);
        Page<OrderDto> orderDtos = iOrderService.findList(buyerOpenid, pageable);
        log.info(orderDtos.getContent().toString());
    }


    @Test
    public void testCancel() throws Exception {

        OrderDto orderDto = new OrderDto();
        orderDto.setId("151525197329459611");
        orderDto.setOrderStatus(OrderStatusEnum.CANCEL.getStatus());

        iOrderService.cancel(orderDto);

    }

    @Test
    public void testFinish() throws Exception {
        OrderDto orderDto = new OrderDto();
        orderDto.setId("aaa");

        iOrderService.finish(orderDto);
    }

    @Test
    public void testPay() throws Exception {
        OrderDto orderDto = new OrderDto();
        orderDto.setId("aaa");
        iOrderService.pay(orderDto);
    }
}