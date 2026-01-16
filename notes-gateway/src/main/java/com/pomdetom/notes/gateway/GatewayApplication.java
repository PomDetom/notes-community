package com.pomdetom.notes.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.pomdetom.notes.common.annotation.EnableJwtUtil;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.pomdetom.notes.gateway.config.JwtProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties.class)
@EnableJwtUtil
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
