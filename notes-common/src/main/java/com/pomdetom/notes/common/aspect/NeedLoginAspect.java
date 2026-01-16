package com.pomdetom.notes.common.aspect;

import com.pomdetom.notes.common.annotation.NeedLogin;
import com.pomdetom.notes.common.context.UserContext;
import com.pomdetom.notes.common.utils.ApiResponseUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class NeedLoginAspect {

    @Around("@annotation(needLogin)")
    public Object around(ProceedingJoinPoint joinPoint, NeedLogin needLogin) throws Throwable {

        if (!UserContext.isLogin()) {
            return ApiResponseUtil.error("用户未登录");
        }

        if (UserContext.getUserId() == null) {
            return ApiResponseUtil.error("用户 ID 异常");
        }
        return joinPoint.proceed();
    }
}
