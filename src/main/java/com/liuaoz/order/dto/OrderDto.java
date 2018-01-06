package com.liuaoz.order.dto;

import com.liuaoz.order.model.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description : TODO
 * @Author : matrix
 * @Date : created in 2018/1/6 21:38
 */
@Data
public class OrderDto {

    private String id;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddr;

    private String buyerOpenid;

    private BigDecimal amount;

    private Integer orderStatus;

    private Integer payStatus;

    private List<OrderDetail> orderDetails;
}
