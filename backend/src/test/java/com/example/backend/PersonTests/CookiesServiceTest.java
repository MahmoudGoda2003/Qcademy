package com.example.backend.PersonTests;

import com.example.backend.services.CookiesService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CookiesServiceTest {
    @Test
    public void testCreateCookie() {
        CookiesService cookiesService = new CookiesService();
        String cookieName = "testCookie";
        String cookieData = "testData";
        int lifeTime = 3600;

        Cookie cookie = cookiesService.createCookie(cookieName, cookieData, lifeTime);

        assertEquals(cookieName, cookie.getName());
        assertEquals(cookieData, cookie.getValue());
        assertEquals(lifeTime, cookie.getMaxAge());
        assertTrue(cookie.isHttpOnly());
    }

    @Test
    public void testGetCookie() {
        CookiesService cookiesService = new CookiesService();

        HttpServletRequest request = mock(HttpServletRequest.class);

        Cookie cookie1 = new Cookie("cookie1", "data1");
        Cookie cookie2 = new Cookie("cookie2", "data2");
        Cookie[] cookies = {cookie1, cookie2};

        when(request.getCookies()).thenReturn(cookies);

        Cookie resultCookie = cookiesService.getCookie(request, "cookie1");
        assertNotNull(resultCookie);
        assertEquals("data1", resultCookie.getValue());

        resultCookie = cookiesService.getCookie(request, "nonExistingCookie");
        assertNull(resultCookie);
    }

}
