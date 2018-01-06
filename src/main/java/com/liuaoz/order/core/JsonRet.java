package com.liuaoz.order.core;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description : TODO
 * @Author : matrix
 * @Date : created in 2018/1/6 21:27
 */
@Data
@NoArgsConstructor
public class JsonRet<T> {

    private Integer status;

    private String msg;

    private T data;

    public JsonRet(RespCode resp) {
        this.status = resp.getStatus();
        this.msg = resp.getMsg();
    }

    public JsonRet(RespCode resp, T data) {
        this.status = resp.getStatus();
        this.msg = resp.getMsg();
        this.data = data;
    }
}
