package com.al.almall.utils;

import cn.hutool.core.util.StrUtil;
import com.al.almall.entity.ShiroUserDetail;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;


@Slf4j
@Component
public class JwtUtil {
    // 密钥
    private final String secretKey = "almall";
    // token 有效期 这里默认7天
    private final long ttl = 7 * 24 * 60 * 60 * 1000;

    public String createToken(Integer userId, String username) {
        String token = Jwts.builder()
                .setSubject("AUTH-USER")
                .setExpiration(new Date(System.currentTimeMillis() + ttl))
                .claim("userId", userId)
                .claim("username", username)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
        return token;
    }

    public String createToken(Integer userId, String username, Integer storeId) {
        String token = Jwts.builder()
                .setSubject("AUTH-USER")
                .setExpiration(new Date(System.currentTimeMillis() + ttl))
                .claim("userId", userId)
                .claim("username", username)
                .claim("storeId", storeId)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
        return token;
    }

    public Integer getUserId(String token) {
        try {
            if (StrUtil.isEmpty(token)) return null;

            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            Integer userId = (Integer) claims.get("userId");
            return userId;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getUsername(String token) {
        try {
            if (StrUtil.isEmpty(token)) return "";

            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            return (String) claims.get("username");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Integer getStoreId(String token) {
        try {
            if (StrUtil.isEmpty(token)) return null;

            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            return (Integer) claims.get("storeId");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 验证token是否还有效
     *
     * @param token       客户端传入的token
     * @param userDetails 从数据库中查询出来的用户信息
     */
    public boolean validateToken(String token, ShiroUserDetail userDetails) {
        String username = getUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * 判断token是否已经失效
     */
    public boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * 从token中获取过期时间
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * 从token中获取JWT中的负载
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.info("JWT格式验证失败:{}", token);
        }
        return claims;
    }

    public static void main(String[] args) {
        JwtUtil jwtUtil = new JwtUtil();
        String adminToken = jwtUtil.createToken(1, "admin");
        System.out.println(adminToken);
        ShiroUserDetail shiroUserDetail = new ShiroUserDetail();
        shiroUserDetail.setId(1);
        shiroUserDetail.setUsername("admin");
        boolean b = jwtUtil.validateToken(adminToken, shiroUserDetail);
        System.out.println(b);
        System.out.println(jwtUtil.getUsername(adminToken));
        System.out.println(jwtUtil.isTokenExpired(adminToken));
    }
}
