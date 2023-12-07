package com.example.backend.person.service;

import com.example.backend.person.model.Person;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class Authenticator {
    private final String secret_key;
    private final long accessTokenValidity = 24 * 60 * 60 * 1000;

    private final JwtParser jwtParser;

    private final String TOKEN_HEADER = "Cookie";

    public Authenticator() {
        this.secret_key = new StandardEnvironment().getProperty("QcademyAuthKey");
        this.jwtParser = Jwts.parser().setSigningKey(this.secret_key);
    }

    public String createToken(Person person, boolean isTeacher, boolean isAdmin) {
        Claims claims = Jwts.claims().setSubject(person.getId() == null ? person.getEmail() : person.getId().toString());
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
}
