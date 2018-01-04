package com.liuaoz.order.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Description : TODO
 * @Author : matrix
 * @Date : created in 2018/1/3 23:34
 */
@Data
@Entity
@DynamicUpdate
public class ProductCategory extends BaseEntity{

    /**
     * 主键
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 类目名称
     */
    private String name;

    /**
     * 类目编号
     */
    private Integer code;

}
