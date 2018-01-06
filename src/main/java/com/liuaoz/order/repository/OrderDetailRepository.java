package com.liuaoz.order.repository;

import com.liuaoz.order.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description : TODO
 * @Author : matrix
 * @Date : created in 2018/1/6 20:20
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    List<OrderDetail> findByOrderId(String orderId);
}
