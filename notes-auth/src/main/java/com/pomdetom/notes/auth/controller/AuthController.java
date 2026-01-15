package com.pomdetom.notes.auth.controller;

import com.pomdetom.notes.api.user.UserService;
import com.pomdetom.notes.common.model.base.ApiResponse;
import com.pomdetom.notes.common.model.dto.user.LoginRequest;
import com.pomdetom.notes.common.model.dto.user.RegisterRequest;
import com.pomdetom.notes.common.model.vo.user.LoginUserVO;
import com.pomdetom.notes.common.model.vo.user.RegisterVO;
import com.pomdetom.notes.common.utils.JwtUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Resource
    private JwtUtil jwtUtil;

    @DubboReference
    private UserService userService;

    @PostMapping("/login")
    public ApiResponse<Map<String, String>> login(@RequestParam String username, @RequestParam String password) {
        LoginRequest request = new LoginRequest();
        request.setAccount(username); // Try to use input as account first
        request.setPassword(password);

        // Call User Service (check password)
        // Note: LoginUserVO in UserService.login result might not contain token if it's generated here.
        // We use it to verify credentials and get userId.
        ApiResponse<LoginUserVO> response = userService.login(request);

        if (response.getCode() != 200 || response.getData() == null) {
            return ApiResponse.error(response.getCode(), response.getMessage());
        }

        Long userId = response.getData().getUserId();

        // Generate Token
        String token = jwtUtil.generateToken(userId);

        return ApiResponse.success(Collections.singletonMap("token", token));
    }

    @PostMapping("/register")
    public ApiResponse<String> register(@RequestParam String username, @RequestParam String password, @RequestParam String email) {
        RegisterRequest request = new RegisterRequest();
        request.setAccount(username);
        request.setUsername(username); // Use same for nickname
        request.setPassword(password);
        request.setEmail(email);
        
        // Call User Service to create user
        ApiResponse<RegisterVO> response = userService.register(request);
        
        if (response.getCode() != 200) {
            return ApiResponse.error(response.getCode(), response.getMessage());
        }
        return ApiResponse.success("User registered successfully");
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Auth Service";
    }
}
