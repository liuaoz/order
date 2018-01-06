package com.liuaoz.order.service;

import com.liuaoz.order.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Description : TODO
 * @Author : matrix
 * @Date : created in 2018/1/6 21:37
 */
public interface IOrderService {

    /**
     * 创建订单
     *
     * @param orderDto
     * @return
     */
    OrderDto create(OrderDto orderDto);

    /**
     * 根据主键查询订单信息
     *
     * @param orderId
     * @return
     */
    OrderDto findOne(String orderId);

    /**
     * 查询买家的所有订单列表
     *
     * @param buyerOpenid
     * @param pageable
     * @return
     */
    Page<OrderDto> findList(String buyerOpenid, Pageable pageable);

    /**
     * 订单支付
     */
    boolean pay(OrderDto orderDto);

}
