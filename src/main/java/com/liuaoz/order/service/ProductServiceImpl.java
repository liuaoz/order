package com.liuaoz.order.service;

import com.liuaoz.order.common.OrderException;
import com.liuaoz.order.common.ProductStatusEnum;
import com.liuaoz.order.core.RespCode;
import com.liuaoz.order.dto.CartDto;
import com.liuaoz.order.model.Product;
import com.liuaoz.order.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional
    public void increaseStock(List<CartDto> cartDtos) {

        for (CartDto cartDto : cartDtos) {

            Product product = productRepository.findOne(cartDto.getProductId());

            if (null == product) {
                throw new OrderException(RespCode.PRODUCT_NOT_EXIST);
            }

            product.setStock(product.getStock() + cartDto.getQuantity());

            productRepository.save(product);

        }

    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDto> cartDtos) {

        for (CartDto cartDto : cartDtos) {
            Product product = productRepository.findOne(cartDto.getProductId());
            if (null == product) {
                throw new OrderException(RespCode.PRODUCT_NOT_EXIST);
            }

            Integer rest = product.getStock() - cartDto.getQuantity();
            //TODO 是否多线程安全？？？
            if (rest < 0) {
                throw new OrderException(RespCode.PRODUCT_NOT_ENOUGH);
            }

            product.setStatus(rest);

            productRepository.save(product);
        }
    }
}
