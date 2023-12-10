package com.example.backend.services;

import com.example.backend.person.model.Person;
import com.example.backend.person.model.Role;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String JWT_SECRET;

    public JwtService() { }

    public String createToken(Person person) {
        return Jwts
                .builder()
                .setSubject(person.getRole().name())
                .setId(person.getId().toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, Role actualRole){
        final Claims claims = extractAllClaims(token);
        final Role role = Role.valueOf(claims.getSubject());
        final Date expirationDate = claims.getExpiration();
        return (role == actualRole) && !(expirationDate.before(new Date()));
    }

    private Claims extractAllClaims(String jwt){
        return Jwts
                .parserBuilder()
                .setSigningKey(this.getSecretKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }

    private Key getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Long extractUserId(String jwt){
        final Claims claims = extractAllClaims(jwt);
        return Long.parseLong(claims.getId());
    }

}
