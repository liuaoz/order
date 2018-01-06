package com.liuaoz.order.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @Description : TODO
 * @Author : matrix
 * @Date : created in 2018/1/4 22:03
 */
@Entity
@Data
@DynamicUpdate
public class Product extends BaseEntity {

    @Id
    private String id;

    private String name;

    private BigDecimal price;

    private Integer stock;

    private String remark;

    private String icon;

    /**
     * 类目编号
     */
    private Integer categoryCode;

    /**
     * 状态：0正常，1下架
     */
    private Integer status;

}
