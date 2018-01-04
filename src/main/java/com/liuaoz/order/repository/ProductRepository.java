package com.liuaoz.order.repository;

import com.liuaoz.order.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description : 商品实体类
 * @Author : matrix
 * @Date : created in 2018/1/4 22:12
 */
public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findByStatus(Integer status);
}
