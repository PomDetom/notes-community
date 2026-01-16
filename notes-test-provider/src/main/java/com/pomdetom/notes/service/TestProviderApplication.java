package com.pomdetom.notes.service;

import com.pomdetom.notes.common.annotation.EnableNeedLoginAspect;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
@EnableNeedLoginAspect
public class TestProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestProviderApplication.class, args);
    }
}
