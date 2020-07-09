package com.jf.school.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * JAVA-JWT工具类
 */
@Component
public class JwtUtil {

    public final static String ACCOUNT = "role";
    public final static String USER_TYPE = "userType";

    private String key = "jfjfjf";

    // 有效期 10 秒
//    private long expirationTime = 10;
    private long expirationTime = 30000;

    // 生成JWT
    public String createJWT(String id, String subject, Integer userType) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder().setId(id)
                .setSubject(subject)
                .setIssuedAt(now)
                .claim(JwtUtil.ACCOUNT, subject)
                .claim(JwtUtil.USER_TYPE, userType)
                .signWith(SignatureAlgorithm.HS256, key);
        builder.setExpiration(new Date(System.currentTimeMillis() + (expirationTime * 1000)));
        return builder.compact();
    }

    // 解析JWT
    public Claims parseJWT(String jwtStr) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwtStr)
                .getBody();
    }

    // 解析JWT
    public String getUserId(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody().getId();
    }

    public Integer getUserType(String token) {
        return Jwts.parser().setSigningKey(key)
                .parseClaimsJws(token)
                .getBody()
                .get(USER_TYPE, Integer.class);
    }

}
