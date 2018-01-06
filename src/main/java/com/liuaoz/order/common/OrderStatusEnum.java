package com.liuaoz.order.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Description : 订单状态
 * @Author : matrix
 * @Date : created in 2018/1/6 20:36
 */
@Getter
@AllArgsConstructor
public enum OrderStatusEnum {

    NEW(0, "新订单"),

    FINISHED(1, "已完成"),

    CANCEL(2, "已取消"),

    DELETE(3, "已删除");

    private Integer status;

    private String desc;

}
