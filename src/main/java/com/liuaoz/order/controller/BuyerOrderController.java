package com.liuaoz.order.controller;

import com.liuaoz.order.core.JsonRet;
import com.liuaoz.order.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description : TODO
 * @Author : matrix
 * @Date : created in 2018/1/7 16:29
 */
@RestController
@RequestMapping("/buy/order")
@Slf4j
public class BuyerOrderController extends BaseController {

    /**
     * 创建订单
     *
     * @param orderDto
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public JsonRet create(OrderDto orderDto) {


        return success();
    }

    /**
     * 订单列表
     */

    /**
     * 订单详情
     */

    /**
     * 取消订单
     */
}
