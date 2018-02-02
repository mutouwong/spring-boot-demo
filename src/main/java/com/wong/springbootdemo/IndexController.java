package com.wong.springbootdemo;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO
 * @Author: gang.wang
 * @Date: Created in 上午6:28 2018/1/23
 */
@RestController
@EnableAutoConfiguration()
public class IndexController {

    /*@Value("${wong.name}")
    private String name;

    @Value("${wong.age}")
    private Integer age;*/

    @Autowired
    private InfoBean infoBean;

    @RequestMapping(value = "/")
    String index(){
        return "Hello Spring Boot!" ;
    }

    @RequestMapping(value = "/info/bean")
    String info1() {
        return JSON.toJSONString(infoBean);
    }

}
