package com.pomdetom.notes.email;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class NotesEmailApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotesEmailApplication.class, args);
    }
}
