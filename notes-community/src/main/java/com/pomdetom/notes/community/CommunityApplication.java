package com.pomdetom.notes.community;

import com.pomdetom.notes.common.annotation.EnableNeedLoginAspect;
import com.pomdetom.notes.common.annotation.EnableNotesWeb;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableDubbo
@EnableNeedLoginAspect
@EnableNotesWeb
public class CommunityApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommunityApplication.class, args);
    }
}
