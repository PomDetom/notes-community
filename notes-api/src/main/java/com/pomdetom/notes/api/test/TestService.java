package com.pomdetom.notes.api.test;

import com.pomdetom.notes.common.model.base.ApiResponse;
import com.pomdetom.notes.common.model.entity.User;

public interface TestService {
    ApiResponse<String> sayHello(String name);

    ApiResponse<User> testRegister();
}
