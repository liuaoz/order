package com.liuaoz.order.controller;

import com.liuaoz.order.core.JsonRet;
import com.liuaoz.order.core.RespCode;

/**
 * @Description : TODO
 * @Author : matrix
 * @Date : created in 2018/1/6 21:31
 */
public abstract class BaseController {

    protected JsonRet success() {
        return new JsonRet(RespCode.SUCCESS);
    }
}
