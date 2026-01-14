package com.pomdetom.notes.common.annotation;

import com.pomdetom.notes.common.interceptor.TokenInterceptor;
import com.pomdetom.notes.common.scope.RequestScopeData;
import com.pomdetom.notes.common.utils.JwtUtil;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({TokenInterceptor.class, RequestScopeData.class, JwtUtil.class})
public @interface EnableTokenInterceptor {
}
