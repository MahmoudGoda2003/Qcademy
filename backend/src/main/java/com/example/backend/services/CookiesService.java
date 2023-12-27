package com.example.backend.services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

@Service
public class CookiesService {

    //TODO:cookie.setSecure(true);enable later when using https
    public Cookie createCookie(String name, String data, int lifeTime) {
        Cookie cookie = new Cookie(name, data);
        cookie.setMaxAge(lifeTime);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        return cookie;
    }

    public Cookie getCookie(HttpServletRequest request, String name) {
        return request.getCookies() == null ? null :
                Arrays.stream(request.getCookies())
                        .filter(cookie -> cookie.getName().equals(name))
                        .findFirst()
                        .orElse(null);
    }

    public String hashCode(String input) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(input.getBytes());

        return Base64.getEncoder().encodeToString(hashBytes);
    }
}
