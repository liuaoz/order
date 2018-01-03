package com.liuaoz.order.repository;

import com.liuaoz.order.BaseTest;
import com.liuaoz.order.model.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description : TODO
 * @Author : matrix
 * @Date : created in 2018/1/3 23:44
 */
@Slf4j
public class ProductCategoryRepositoryTest extends BaseTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void testSave() {

        ProductCategory productCategory = new ProductCategory();
        productCategory.setName("测试类目1");
        productCategory.setType(1);

        productCategoryRepository.save(productCategory);
    }

    @Test
    public void testUpdate() {

        ProductCategory productCategory = productCategoryRepository.findOne(4);

        productCategory.setType(30);

        productCategoryRepository.save(productCategory);
    }

    @Test
    public void testFindList() {
        List<Integer> types = new ArrayList<>();
        types.add(1);
        types.add(2);
        List<ProductCategory> list = productCategoryRepository.findByTypeIn(types);

        list.forEach(item -> log.info(item.getName()));
    }

}