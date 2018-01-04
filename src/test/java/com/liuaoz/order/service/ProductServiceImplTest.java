package com.liuaoz.order.service;

import com.liuaoz.order.BaseTest;
import com.liuaoz.order.model.Product;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Description : TODO
 * @Author : matrix
 * @Date : created in 2018/1/4 22:53
 */
public class ProductServiceImplTest extends BaseTest {

    @Autowired
    private IProductService iProductService;

    @Test
    public void findOne() throws Exception {

        Product product = iProductService.findOne("aaaaaaaaaaa");

        Assert.assertEquals("aaaaaaaaaaa", product.getId());
    }

    @Test
    public void findOnAll() throws Exception {

        List<Product> list = iProductService.findOnAll();

        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void findAll() throws Exception {

        PageRequest request = new PageRequest(0, 10);

        Page<Product> list = iProductService.findAll(request);

        Assert.assertTrue(list.getSize() > 0);
    }


    @Test
    public void save() throws Exception {

        Product product = new Product();

        product.setId("abcdefg");
        product.setName("佛跳墙");
        product.setPrice(new BigDecimal(100));
        product.setStock(100);
        product.setCategoryCode(1);
        product.setStatus(0);
        product.setRemark("很好吃的哦");
        product.setIcon("www.sina.com");

        Product ret = iProductService.save(product);

        Assert.assertEquals("abcdefg", ret.getId());
    }


}