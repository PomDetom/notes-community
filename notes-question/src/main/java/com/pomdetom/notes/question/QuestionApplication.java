package com.pomdetom.notes.question;

import com.pomdetom.notes.common.annotation.EnableNeedLoginAspect;
import com.pomdetom.notes.common.annotation.EnableNotesWeb;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
@EnableNeedLoginAspect
@EnableNotesWeb
public class QuestionApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuestionApplication.class, args);
    }
}
