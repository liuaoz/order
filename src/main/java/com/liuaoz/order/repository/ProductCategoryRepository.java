package com.liuaoz.order.repository;

import com.liuaoz.order.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description : TODO
 * @Author : matrix
 * @Date : created in 2018/1/3 23:40
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

    List<ProductCategory> findByTypeIn(List<Integer> types);
}
