package com.liuaoz.order.service;

import com.liuaoz.order.model.ProductCategory;
import com.liuaoz.order.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description : TODO
 * @Author : matrix
 * @Date : created in 2018/1/4 00:20
 */
@Service
public class ProductCategoryServiceImpl implements IProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public ProductCategory findOne(Integer id) {
        return productCategoryRepository.findOne(id);
    }

    @Override
    public void save(ProductCategory entity) {
        productCategoryRepository.save(entity);
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryRepository.findAll();
    }

    @Override
    public List<ProductCategory> findByTypeIn(List<Integer> types) {
        return productCategoryRepository.findByCodeIn(types);
    }
}
