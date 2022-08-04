package com.revature.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

import com.revature.models.User;

public class JWTUtil {
    private static final String secret = "tWuXJzrSzbY/0AZDvgqiwC6Y90MxvYv/dIZOUsxX50Q="; // this should be regenerated and put into environment variable
    private static final SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    public static String getNewSecret() {
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String secretString = Encoders.BASE64.encode(key.getEncoded());
        return secretString;
    }

    public static String generateUserToken(User user) {
        
        String jws = Jwts.builder()
            .setSubject(Integer.toString(user.getId()))
            .claim("email", user.getEmail())
            .claim("username", user.getUsername())
            .signWith(key)
            .compact();
        return jws;
    }

    // taken from io.jsonwebtoken documentation 
    public static String verifyUserToken(String token) {
        Jws<Claims> jws = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()        
            .parseClaimsJws(token);
        return jws.getBody().get("sub").toString();
    }
}