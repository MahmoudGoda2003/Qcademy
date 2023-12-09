package com.example.backend.services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;

public class CookiesService {

    //TODO:cookie.setSecure(true);enable later when using https
    public Cookie createCookie(String name, String data, int lifeTime) {
        Cookie cookie = new Cookie(name, data);
        cookie.setMaxAge(lifeTime);
        cookie.setHttpOnly(true);
        return cookie;
    }

    public Cookie getCookie(HttpServletRequest request, String name) {
        return request.getCookies() == null ? null :
                Arrays.stream(request.getCookies())
                        .filter(cookie -> cookie.getName().equals(name))
                        .findFirst()
                        .orElse(null);
    }
}
