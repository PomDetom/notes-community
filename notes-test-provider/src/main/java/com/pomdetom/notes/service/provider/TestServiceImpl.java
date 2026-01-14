package com.pomdetom.notes.service.provider;

import com.pomdetom.notes.api.test.TestService;
import com.pomdetom.notes.service.mapper.TestServiceMapper;
import com.pomdetom.notes.common.model.entity.User;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Slf4j
@DubboService
public class TestServiceImpl implements TestService {

    @Resource
    private TestServiceMapper testServiceMapper;

    @Override
    public String sayHello(String name) {
        User user = testServiceMapper.getUser();
        return "Hello " + name + " " + user;
    }

    @GlobalTransactional
    @Override
    public void testRegister() {
        User user = new User();
        user.setAccount("test123456");
        user.setPassword("test123456");
        user.setGender(1);
        user.setIsBanned(0);
        user.setIsAdmin(0);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        testServiceMapper.insert(user);
        throw new RuntimeException("测试触发 seata 全局回滚");
    }
}
