package com.example.backend.Person.service;

import com.example.backend.Person.model.Person;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class Authenticator {
    private final String secret_key = "ufpdajqlsbcituopjhsaxdzvnjkl";
    private long accessTokenValidity = 24*60*60*1000;

    private final JwtParser jwtParser;

    private final String TOKEN_HEADER = "Cookie";

    public Authenticator(){
        this.jwtParser = Jwts.parser().setSigningKey(secret_key);
    }

    public String createToken(Person person, boolean isTeacher, boolean isAdmin) {
        Claims claims = Jwts.claims().setSubject(person.getId().toString());
        claims.put("email", person.getEmail());
        claims.put("isTeacher", isTeacher);
        claims.put("isAdmin", isAdmin);
        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(accessTokenValidity));
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(tokenValidity)
                .signWith(SignatureAlgorithm.HS256, secret_key)
                .compact();
    }

    private Claims parseJwtClaims(String token) {
        return jwtParser.parseClaimsJws(token).getBody();
    }

    public Claims resolveClaims(HttpServletRequest req) {
        try {
            String token = resolveToken(req);
            if (token != null) {
                return parseJwtClaims(token);
            }
            return null;
        } catch (ExpiredJwtException ex) {
            req.setAttribute("expired", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            req.setAttribute("invalid", ex.getMessage());
            throw ex;
        }
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader(TOKEN_HEADER);
    }

    public boolean validateClaims(Claims claims) throws AuthenticationException {
        try {
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            throw e;
        }
    }

    public String getId(Claims claims) {
        return claims.getSubject();
    }

    private String getEmail(Claims claims) {
        return (String) claims.get("email");
    }
}
