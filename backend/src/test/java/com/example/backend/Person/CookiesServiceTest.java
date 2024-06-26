package com.example.backend.Person;

import com.example.backend.services.CookiesService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CookiesServiceTest {


    private final CookiesService cookiesService = new CookiesService();

    @Test
    public void testCreateCookie() {
        String cookieName = "testCookie";
        String cookieData = "testData";
        int lifeTime = 3600;

        Cookie cookie = this.cookiesService.createCookie(cookieName, cookieData, lifeTime);

        assertEquals(cookieName, cookie.getName());
        assertEquals(cookieData, cookie.getValue());
        assertEquals(lifeTime, cookie.getMaxAge());
        assertTrue(cookie.isHttpOnly());
    }

    @Test
    public void testGetCookie() {

        HttpServletRequest request = mock(HttpServletRequest.class);

        Cookie cookie1 = new Cookie("cookie1", "data1");
        Cookie cookie2 = new Cookie("cookie2", "data2");
        Cookie[] cookies = {cookie1, cookie2};

        when(request.getCookies()).thenReturn(cookies);

        Cookie resultCookie = this.cookiesService.getCookie(request, "cookie1");
        assertNotNull(resultCookie);
        assertEquals("data1", resultCookie.getValue());

        resultCookie = this.cookiesService.getCookie(request, "nonExistingCookie");
        assertNull(resultCookie);
    }

    @Test
    public void testHashCode() throws Exception {
        String input = "test";
        String hashedInput = this.cookiesService.hashCode(input);
        assertEquals(hashedInput, this.cookiesService.hashCode(input));
    }

}
