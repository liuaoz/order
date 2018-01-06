package com.liuaoz.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description : 购物车信息
 * @Author : matrix
 * @Date : created in 2018/1/6 22:46
 */
@Data
@AllArgsConstructor
public class CartDto {

    private String productId;

    private Integer quantity;
}
