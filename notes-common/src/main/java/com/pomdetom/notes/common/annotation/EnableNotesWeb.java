package com.pomdetom.notes.common.annotation;

import com.pomdetom.notes.common.config.GlobalExceptionConfig;
import com.pomdetom.notes.common.config.MvcConfig;
import com.pomdetom.notes.common.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ TokenInterceptor.class, GlobalExceptionConfig.class, MvcConfig.class })
public @interface EnableNotesWeb {
}
