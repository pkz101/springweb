package com.gedi.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import javax.crypto.SecretKey;

public class JwtUtils {

    private static String signKey = "this-is-a-secure-secret-key-for-jwt-token-generation-2024";
    private static Long expire = 43200000L;
    private static final SecretKey secretKey = Keys.hmacShaKeyFor(signKey.getBytes(StandardCharsets.UTF_8));

    /**
     * 生成 JWT 令牌
     * @return
     */
    public static String generateJwt(Map<String,Object> claims){
        String jwt = Jwts.builder()
                .claims(claims)
                .subject("user")
                .expiration(new Date(System.currentTimeMillis() + expire))
                .signWith(secretKey)
                .compact();
        return jwt;
    }

    /**
     * 解析 JWT 令牌
     * @param jwt JWT 令牌
     * @return JWT 第二部分负载 payload 中存储的内容
     */
    public static Claims parseJWT(String jwt){
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
        return claims;
    }
}
