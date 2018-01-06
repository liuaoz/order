package com.liuaoz.order.common;

import com.liuaoz.order.core.RespCode;

/**
 * @Description : 异常基类
 * @Author : matrix
 * @Date : created in 2018/1/6 21:26
 */
public class OrderException extends RuntimeException {

    private Integer code;

    public OrderException(RespCode resp) {
        super(resp.getMsg());
        this.code = resp.getStatus();
    }
}
