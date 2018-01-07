package com.liuaoz.order.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description : TODO
 * @Author : matrix
 * @Date : created in 2018/1/6 21:28
 */
@Getter
@AllArgsConstructor
public enum RespCode {
    //通用响应信息
    SUCCESS(200, "Success"),
    NOT_FOUND(404, "Not found"),
    INNER_ERR(500, "Inner error"),

    //业务响应信息
    PRODUCT_NOT_EXIST(1000, "产品不存在"),

    PRODUCT_NOT_ENOUGH(1001, "库存不够"),

    ORDER_NOT_EXIST(1002, "订单不存在"),

    ORDER_DETAIL_NOT_EXIST(1003, "订单详情不存在"),

    ORDER_STATUS_ERROR(1004, "订单状态不正确"),

    ORDER_STATUS_FINISHED(1005, "订单已完成"),

    ORDER_PAY_STATUS_ERROR(1006,"订单支付状态不正确"),
    ;

    private Integer status;

    private String msg;
}
