package com.pomdetom.notes.common.interceptor;

import com.pomdetom.notes.common.context.UserContext;
import com.pomdetom.notes.common.utils.JwtUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenInterceptor implements HandlerInterceptor {

    @Resource
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String token = request.getHeader("Authorization");

        if (token == null) {
            return true;
        }

        token = token.replace("Bearer ", "");

        if (jwtUtil.validateToken(token)) {
            Long userId = jwtUtil.getUserIdFromToken(token);
            UserContext.setUserId(userId);
            UserContext.setToken(token);
        }
        // Token 无效则不设置 Context，视为未登录
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        UserContext.clear();
    }
}
