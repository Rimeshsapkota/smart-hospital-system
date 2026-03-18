//package com.smarthospital.authservice;
//
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.context.annotation.Configuration;
//
//import java.security.Key;
//import java.util.Date;
//
//@Configuration
//public class JwtUtil {
//
//    private static final String SECRET =
//            "mySuperSecretKeyForJwtGeneration123456";
//
//    private static final Key KEY =
//            Keys.hmacShaKeyFor(SECRET.getBytes());
//
//    public  String generateToken(String username) {
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(
//                        System.currentTimeMillis() + 86400000))
//                .signWith(KEY)
//                .compact();
//    }
//    public static String extractUsername(String token) {
//
//        return Jwts.parserBuilder()
//                .setSigningKey(KEY)
//                .build()
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
//    }
//}