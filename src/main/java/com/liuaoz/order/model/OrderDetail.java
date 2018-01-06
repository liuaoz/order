package com.liuaoz.order.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @Description : 订单详情实体
 * @Author : matrix
 * @Date : created in 2018/1/6 19:59
 */
@Entity
@Table
@Data
@DynamicUpdate
public class OrderDetail extends BaseEntity {

    @Id
    private String id;

    private String orderId;

    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productQuantity;

    private String productIcon;

}
