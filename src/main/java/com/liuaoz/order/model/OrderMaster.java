package com.liuaoz.order.model;

import com.liuaoz.order.common.OrderStatusEnum;
import com.liuaoz.order.common.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @Description : 订单信息实体
 * @Author : matrix
 * @Date : created in 2018/1/6 19:58
 */
@Entity
@Table
@Data
@DynamicUpdate
public class OrderMaster extends BaseEntity {

    @Id
    private String id;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddr;

    private String buyerOpenid;

    private BigDecimal amount;

    private Integer orderStatus = OrderStatusEnum.NEW.getStatus();

    private Integer payStatus = PayStatusEnum.UN_PAY.getStatus();
}
