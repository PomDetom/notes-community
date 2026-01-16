package com.pomdetom.notes.user;

import com.pomdetom.notes.common.annotation.EnableNeedLoginAspect;
import com.pomdetom.notes.common.annotation.EnableNotesPassword;
import com.pomdetom.notes.common.annotation.EnableNotesWeb;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableDubbo
@EnableNeedLoginAspect
@EnableNotesPassword
@EnableNotesWeb
public class NotesUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotesUserApplication.class, args);
    }
}
