package com.pomdetom.notes.gateway.filter;

import com.pomdetom.notes.common.utils.JwtUtil;
import com.pomdetom.notes.gateway.config.JwtProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@Slf4j
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private JwtProperties jwtProperties;

    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();

        // 白名单
        for (String url : jwtProperties.getWhitelist()) {
            if (pathMatcher.match(url, path)) {
                log.info("请求路径在白名单内:{}", url);
                return chain.filter(exchange);
            }
        }

        // Token
        String token = request.getHeaders().getFirst("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            log.info("Authorization未携带token");
            return unauthorized(exchange);
        }

        token = token.substring(7);
        if (!jwtUtil.validateToken(token)) {
            return unauthorized(exchange);
        }

        // uid
        Long userId = jwtUtil.getUserIdFromToken(token);
        if (userId == null) {
            log.info("token中无uid");
            return unauthorized(exchange);
        }

        ServerHttpRequest mutatedRequest = request.mutate()
                .header("User-Id", String.valueOf(userId))
                .build();

        return chain.filter(exchange.mutate().request(mutatedRequest).build());
    }

    private Mono<Void> unauthorized(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
