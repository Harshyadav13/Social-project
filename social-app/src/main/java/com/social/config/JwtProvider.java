package com.social.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtProvider {

    private  static  SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    public static String generateToken(Authentication auth) {

        String jwt = Jwts.builder()
                .setIssuer("Codewithharsh").setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 86400000))
                .claim("email", auth.getName() )
                .signWith(key)
                .compact();

                return jwt;
    }

//    public static String getEmailFromJwtToken(String jwt) {
//        //Bearer token
//        jwt = jwt.substring(7);
//
//        Claims claims = Jwts.parserBuilder()
//                        .setSigningKey(key).build().parseClaimsJwt(jwt).getBody();
//
//
//        String email = String.valueOf(claims.get("email"));
//
//        return email;
//    }
public static String getEmailFromJwtToken(String jwt) {
    // Ensure the token starts with "Bearer "
    if (jwt.startsWith("Bearer ")) {
        jwt = jwt.substring(7);
    } else {
        throw new IllegalArgumentException("JWT does not have Bearer prefix");
    }

    Claims claims = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(jwt)  // Use parseClaimsJws for signed JWTs
            .getBody();

    return claims.get("email", String.class);  // Use the correct type for "email"
}
}
