package com.liuaoz.order.service;

import com.liuaoz.order.model.ProductCategory;

import java.util.List;

/**
 * @Description : TODO
 * @Author : matrix
 * @Date : created in 2018/1/4 00:18
 */
public interface IProductCategoryService {

    ProductCategory findOne(Integer id);

    void save(ProductCategory entity);

    List<ProductCategory> findAll();

    List<ProductCategory> findByTypeIn(List<Integer> types);

}
