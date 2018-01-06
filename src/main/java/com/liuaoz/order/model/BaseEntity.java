package com.liuaoz.order.model;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @Description : 实体基类
 * @Author : matrix
 * @Date : created in 2018/1/3 23:36
 */
@Data
@MappedSuperclass
public abstract class BaseEntity {

    public Date createTime;

    public Date updateTime;
}
