package com.liuaoz.order;

import com.liuaoz.order.controller.DemoController;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @Description : TODO
 * @Author : matrix
 * @Date : created in 2018/1/8 21:17
 */

@SpringBootTest(classes = DemoController.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class BootTest {
}
