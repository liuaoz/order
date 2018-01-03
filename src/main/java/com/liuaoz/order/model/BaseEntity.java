package com.liuaoz.order.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @Description : TODO
 * @Author : matrix
 * @Date : created in 2018/1/3 23:36
 */
@Data
@MappedSuperclass
@DynamicUpdate //TODO 数据库更新时间没有更新，没达到效果
public abstract class BaseEntity {

    public Date createTime;

    public Date updateTime;
}
