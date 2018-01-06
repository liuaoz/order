package com.liuaoz.order.repository;

import com.liuaoz.order.BaseTest;
import com.liuaoz.order.model.OrderMaster;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.junit.Assert.*;

/**
 * @Description : TODO
 * @Author : matrix
 * @Date : created in 2018/1/6 21:01
 */
@Slf4j
public class OrderMasterRepositoryTest extends BaseTest {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void findByBuyerOpenid() throws Exception {
        PageRequest request = new PageRequest(0, 10);
        Page<OrderMaster> pageList = orderMasterRepository.findByBuyerOpenid("asdf2323ss0adf", request);

        Assert.assertEquals(0, pageList.getContent().size());

    }

}