package com.surge.util;

import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class JwtUtil {

    private static final int TOKEN_EXPIRE_OUT = 86400;

    private static final int TOKEN_REFRESH_TIME = 604800;

    private static final String TOKEN_SECRET_STRING = "aRoJCsqvf0uDBYHJC32rOLI6ONJdjb5cGEnv9A0Yqjy02n4KOdIy1KvONuNCXoh2nuQ6SX/aSpcGMbjOGq7rwQ==";

    public static String buildToken(Long id) {
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setSubject("system")
                .setIssuer("surge")
                .setAudience("app")
                .compressWith(CompressionCodecs.GZIP)
                .signWith(JwtUtil.getSecretKey(), SignatureAlgorithm.HS512)
                .setExpiration(new Date(System.currentTimeMillis() + JwtUtil.TOKEN_EXPIRE_OUT * 1000))
                .addClaims(Map.of("id", id))
                .compact();
    }

    public static Jws<Claims> verifyToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(JwtUtil.getSecretKey())
                .build()
                .parseClaimsJws(token);
    }

    private static SecretKey getSecretKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.TOKEN_SECRET_STRING);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "HmacSHA512");
    }

}
