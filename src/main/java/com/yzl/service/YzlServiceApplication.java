package com.yzl.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yzl.service.mapper")
public class YzlServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(YzlServiceApplication.class, args);
    }

}
