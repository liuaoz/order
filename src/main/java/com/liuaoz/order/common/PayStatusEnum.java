package com.liuaoz.order.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description : 订单支付状态
 * @Author : matrix
 * @Date : created in 2018/1/6 20:40
 */
@Getter
@AllArgsConstructor
public enum PayStatusEnum {

    UN_PAY(0,"未支付"),
    PAYED(1,"已支付");

    private Integer status;

    private String desc;
}
