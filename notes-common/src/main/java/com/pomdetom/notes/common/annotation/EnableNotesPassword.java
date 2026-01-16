package com.pomdetom.notes.common.annotation;

import com.pomdetom.notes.common.config.PasswordConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(PasswordConfig.class)
public @interface EnableNotesPassword {
}
