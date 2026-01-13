package com.pomdetom.notes.provider;

import com.pomdetom.notes.test.service.TestService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

@DubboService
public class TestServiceImpl implements TestService {
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
