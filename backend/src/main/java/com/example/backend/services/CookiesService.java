package com.example.backend.services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class CookiesService {

    //TODO:cookie.setSecure(true);enable later when using https
    public Cookie createCookie(String name, String data, int lifeTime) {
        Cookie cookie = new Cookie(name, data);
        cookie.setMaxAge(lifeTime);
        cookie.setHttpOnly(true);
        return cookie;
    }

    public Cookie deleteCookie(String name) {
        Cookie cookie = new Cookie(name, null);
        cookie.setMaxAge(0);
        return cookie;
    }

    public Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                return cookie;
            }
        }
        return null;
    }
}
