package com.pomdetom.notes.service;

import com.pomdetom.notes.common.annotation.EnableNeedLoginAspect;
import com.pomdetom.notes.common.annotation.EnableTokenInterceptor;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
@EnableNeedLoginAspect
@EnableTokenInterceptor
public class TestProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestProviderApplication.class, args);
    }
}
