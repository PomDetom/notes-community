package com.pomdetom.notes.common.annotation;

import com.pomdetom.notes.common.utils.JwtUtil;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(JwtUtil.class)
public @interface EnableJwtUtil {
}
