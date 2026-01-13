package com.pomdetom.notes.provider;

import com.pomdetom.notes.mapper.TestServiceMapper;
import com.pomdetom.notes.test.model.entity.User;
import com.pomdetom.notes.test.service.TestService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@DubboService
public class TestServiceImpl implements TestService {

    @Resource
    private TestServiceMapper testServiceMapper;

    @Override
    public String sayHello(String name) {
        User user = testServiceMapper.getUser();
        return "Hello " + name + " " + user;
    }
}
