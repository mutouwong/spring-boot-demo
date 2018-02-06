package com.wong.springbootdemo.controller;

import com.alibaba.fastjson.JSON;
import com.wong.springbootdemo.dto.InfoBean;
import com.wong.springbootdemo.model.SysRole;
import com.wong.springbootdemo.service.SysRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: RestController
 * @Author: gang.wang
 * @Date: Created in 上午6:28 2018/1/23
 */
@RestController
@EnableAutoConfiguration()
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    /*@Value("${wong.name}")
    private String name;

    @Value("${wong.age}")
    private Integer age;*/

    @Autowired
    private InfoBean infoBean;

    @Autowired
    private SysRoleService service;

    @RequestMapping(value = "/sys")
    String index(){

        /*List<SysRole> role = mapper.selectAll();

        role.stream().forEach(n -> System.out.println(n.getName()));*/

        SysRole role = new SysRole();
        role.setName("123123123");
        role.setSeq(9);
        role.setIsdefault(0);

        try {
            service.addSysRole(role);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Hello Spring Boot!" ;
    }

    @RequestMapping(value = "/info/bean")
    String info1() {
        logger.info("访问/info/bean,输出为[{}]",JSON.toJSONString(infoBean));

        return JSON.toJSONString(infoBean);
    }

}
