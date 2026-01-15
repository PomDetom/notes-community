package com.pomdetom.notes.common.config;

import com.pomdetom.notes.common.exception.ParamExceptionHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@Import(ParamExceptionHandler.class)
public class GlobalExceptionConfig {
}
