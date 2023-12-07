package com.example.backend.Services;

import com.example.backend.Person.model.Person;
import com.example.backend.Person.model.Role;
import com.example.backend.Person.service.PersonService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    @Value("${JwtSecret}")
    private static String JWT_SECRET;

    public JwtService() { }

    public String createToken(Person person, boolean isTeacher, boolean isAdmin) {
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

    private static Claims extractAllClaims(String jwt){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }

    private static Key getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Long extractUserId(String jwt){
        final Claims claims = extractAllClaims(jwt);
        return Long.parseLong(claims.getId());
    }

}
