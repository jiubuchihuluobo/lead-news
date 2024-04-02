package com.surge.util.common;

import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class JwtUtil {

    private static final int TOKEN_EXPIRE_OUT = 86400;

    private static final int TOKEN_REFRESH_TIME = 300;

    private static final String TOKEN_SECRET_STRING = "aRoJCsqvf0uDBYHJC32rOLI6ONJdjb5cGEnv9A0Yqjy02n4KOdIy1KvONuNCXoh2nuQ6SX/aSpcGMbjOGq7rwQ==";

    public static String buildToken(Long id) {
        Map<String, Object> claimMaps = Map.of("id", id);
        long currentTime = System.currentTimeMillis();
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date(currentTime))
                .setSubject("system")
                .setIssuer("surge")
                .setAudience("app")
                .compressWith(CompressionCodecs.GZIP)
                .signWith(JwtUtil.getSecretKey(), SignatureAlgorithm.HS512)
                .setExpiration(new Date(currentTime + JwtUtil.TOKEN_EXPIRE_OUT * 1000))
                .addClaims(claimMaps)
                .compact();
    }

    private static Jws<Claims> getJws(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(JwtUtil.getSecretKey())
                .build()
                .parseClaimsJws(token);
    }

    public static Claims getClaims(String token) {
        try {
            return JwtUtil.getJws(token).getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public static JwsHeader<?> getHeader(String token) {
        try {
            return JwtUtil.getJws(token).getHeader();
        } catch (Exception e) {
            return null;
        }
    }

    public static int verifyToken(Claims claims) {
        if (claims == null) {
            return 1;
        }
        try {
            if ((claims.getExpiration().getTime() - System.currentTimeMillis()) > JwtUtil.TOKEN_REFRESH_TIME * 1000) {
                return -1;
            } else {
                return 0;
            }
        } catch (ExpiredJwtException e) {
            return 1;
        } catch (Exception e) {
            return 2;
        }
    }

    public static SecretKey getSecretKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.TOKEN_SECRET_STRING);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "HmacSHA512");
    }

    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzUxMiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAA_yWLUQpCIRAA77LfT3B9a6vvNmtZGATSKhTR3Vvpb4ZhPnAfDQ6oPhBjFEchZUdEyUmJ2YV8Ln5PyV8zwwZNBhzIGHxE5tMGOovd-tZRH6urLp3PWzWTeTGT3o3rq_9P3BOus1nD7w9G7HoQgAAAAA.4kT3Ajf7QgrJJ45lZimmP_IaRDx_ejF9kbK-joxA1ti7TCUKuQKwvB9Gg-GMAO7jNU_ETT8rAIdHxmkkXDWZug";
        System.out.println(JwtUtil.getHeader(token));
        System.out.println(JwtUtil.getClaims(token));
        System.out.println(JwtUtil.verifyToken(JwtUtil.getClaims(token)));
    }

}
