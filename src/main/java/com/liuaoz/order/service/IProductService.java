package com.liuaoz.order.service;

import com.liuaoz.order.dto.CartDto;
import com.liuaoz.order.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Description : 商品操作服务接口
 * @Author : matrix
 * @Date : created in 2018/1/4 22:21
 */
public interface IProductService {

    Product findOne(String id);

    List<Product> findOnAll();

    Page<Product> findAll(Pageable pageable);

    Product save(Product product);

    //TODO 减库存
    void increaseStock(List<CartDto> cartDtos);

    // TODO 加库存
    void decreaseStock(List<CartDto> cartDtos);
}

