package com.liuaoz.order.common;

import lombok.Getter;

/**
 * @Description : 商品状态
 * @Author : matrix
 * @Date : created in 2018/1/4 22:24
 */
@Getter
public enum ProductStatusEnum {

    ON(0, "正常"),//
    OFF(1, "下架")//
    ;

    private Integer status;

    private String desc;

    ProductStatusEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

}
