package com.pomdetom.notes.service.controller;

import com.pomdetom.notes.api.test.TestService;
import com.pomdetom.notes.common.model.base.ApiResponse;
import com.pomdetom.notes.common.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private TestService testService;

    @GetMapping("/hello")
    public String testSayHello() {
        return testService.sayHello("world");
    }

}
