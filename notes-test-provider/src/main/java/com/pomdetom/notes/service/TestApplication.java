package com.pomdetom.notes.service;

import com.pomdetom.notes.common.annotation.EnableNeedLoginAspect;
import com.pomdetom.notes.common.annotation.EnableNotesWeb;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
@EnableNeedLoginAspect
@EnableNotesWeb
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}
