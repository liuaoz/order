package com.liuaoz.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description : TODO
 * @Author : matrix
 * @Date : created in 2018/1/3 22:12
 */
@Controller
@RequestMapping("/demo/")
@Slf4j
public class Demo {

    @RequestMapping("test")
    @ResponseBody
    public String test(){
        log.info("demo test....");
        return "success";
    }
}
