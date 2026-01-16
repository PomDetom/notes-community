package com.pomdetom.notes.common.context;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * 用户上下文，用于存放当前请求的用户信息
 * 使用 TransmittableThreadLocal 以支持在异步线程池中传递
 */
public class UserContext {
    private static final ThreadLocal<Long> USER_ID = new TransmittableThreadLocal<>();
    private static final ThreadLocal<String> TOKEN = new TransmittableThreadLocal<>();

    public static void setUserId(Long userId) {
        USER_ID.set(userId);
    }

    public static Long getUserId() {
        return USER_ID.get();
    }

    public static void setToken(String token) {
        TOKEN.set(token);
    }

    public static String getToken() {
        return TOKEN.get();
    }

    public static void clear() {
        USER_ID.remove();
        TOKEN.remove();
    }

    public static boolean isLogin() {
        return getUserId() != null;
    }
}
