package com.wong.springbootdemo.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Description: 配置文件对应实体
 * @Author: gang.wang
 * @Date: Created in 上午11:30 2018/1/23
 */
@Component
@PropertySource("classpath:info.properties")
@ConfigurationProperties(prefix = "info")
public class InfoBean {

    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "InfoBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
