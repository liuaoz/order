package com.liuaoz.order.repository;

import com.liuaoz.order.BaseTest;
import com.liuaoz.order.model.Product;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description : TODO
 * @Author : matrix
 * @Date : created in 2018/1/4 22:27
 */
public class ProductRepositoryTest extends BaseTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testSave() {

        Product product = new Product();
        product.setName("兰州拉面");
        product.setId("sdflasdjf");
        product.setCategoryCode(1);
        product.setStock(10);
        product.setRemark("很好吃的。。。");
        product.setPrice(new BigDecimal("10"));
        productRepository.save(product);
    }

    @Test
    public void testUpdate() {
        Product product = productRepository.findOne("aaaaaaaaaaa");

        product.setIcon("www.sinab.com");

        productRepository.save(product);


    }

    @Test
    public void testFindByStatus() {

        List<Product> list =
                productRepository.findByStatus(0);
        Assert.assertTrue(list.size() > 0);

    }

}