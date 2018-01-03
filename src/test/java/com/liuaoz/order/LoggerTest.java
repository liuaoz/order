package com.liuaoz.order;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description : TODO
 * @Author : matrix
 * @Date : created in 2018/1/3 21:46
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {

    @Test
    public void test1() {
        log.debug("test debug logger...");
        log.info("test info logger ...");
        log.error("test error logger...");
    }
}
