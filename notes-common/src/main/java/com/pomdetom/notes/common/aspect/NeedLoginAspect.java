package com.pomdetom.notes.common.aspect;

import com.pomdetom.notes.common.annotation.NeedLogin;
import com.pomdetom.notes.common.scope.RequestScopeData;
import com.pomdetom.notes.common.utils.ApiResponseUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import javax.annotation.Resource;

@Aspect
public class NeedLoginAspect {

    @Resource
    private RequestScopeData requestScopeData;

    @Around("@annotation(needLogin)")
    public Object around(ProceedingJoinPoint joinPoint, NeedLogin needLogin) throws Throwable {

        if (!requestScopeData.isLogin()) {
            return ApiResponseUtil.error("用户未登录");
        }

        if (requestScopeData.getUserId() == null) {
            return ApiResponseUtil.error("用户 ID 异常");
        }
        return joinPoint.proceed();
    }
}

