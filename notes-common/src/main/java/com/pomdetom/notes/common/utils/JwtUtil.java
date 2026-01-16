package com.pomdetom.notes.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 生成JWT令牌
     */
    public String generateToken(Long userId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 从token中获取用户ID
     */
    public Long getUserIdFromToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            return Long.valueOf(claims.get("userId").toString());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 验证token是否有效 (返回布尔值，兼容旧代码)
     */
    public boolean validateToken(String token) {
        try {
            validateTokenThrows(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 验证token是否有效 (抛出具体异常)
     * 
     * @throws io.jsonwebtoken.ExpiredJwtException   Token过期
     * @throws io.jsonwebtoken.SignatureException    签名无效
     * @throws io.jsonwebtoken.MalformedJwtException Token格式错误
     */
    public void validateTokenThrows(String token) {
        Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
    }

    /**
     * 刷新JWT令牌
     */
    public String refreshToken(Long userId) {
        return generateToken(userId);
    }
}