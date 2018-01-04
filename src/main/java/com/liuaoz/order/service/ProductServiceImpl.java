package com.liuaoz.order.service;

import com.liuaoz.order.common.ProductStatusEnum;
import com.liuaoz.order.model.Product;
import com.liuaoz.order.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description : 商品操作服务实现类
 * @Author : matrix
 * @Date : created in 2018/1/4 22:46
 */
@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product findOne(String id) {
        return productRepository.findOne(id);
    }

    @Override
    public List<Product> findOnAll() {
        return productRepository.findByStatus(ProductStatusEnum.ON.getStatus());
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }
}
