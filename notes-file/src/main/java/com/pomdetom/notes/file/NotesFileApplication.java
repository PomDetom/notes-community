package com.pomdetom.notes.file;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class NotesFileApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotesFileApplication.class, args);
    }
}
