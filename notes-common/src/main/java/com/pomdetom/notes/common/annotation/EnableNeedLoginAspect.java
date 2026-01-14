package com.pomdetom.notes.common.annotation;

import com.pomdetom.notes.common.aspect.NeedLoginAspect;
import com.pomdetom.notes.common.scope.RequestScopeData;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({NeedLoginAspect.class, RequestScopeData.class})
public @interface EnableNeedLoginAspect {
}
