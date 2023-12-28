package com.example.backend.Person.Security;

import com.example.backend.config.JwtAuthenticationEntryPoint;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.AuthenticationException;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Mockito.*;
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class EntryPointException {

    @InjectMocks
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testCommence() throws Exception {
        // Mock HttpServletRequest, HttpServletResponse, and AuthenticationException
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        AuthenticationException authException = mock(AuthenticationException.class);
        // Call the commence method
        jwtAuthenticationEntryPoint.commence(request, response, authException);
        // Verify that the response has been set with the expected status and error message
        verify(response, times(1)).sendError(HttpServletResponse.SC_FORBIDDEN, "Unauthorized");
        // Verify that no other interactions occurred
        verifyNoMoreInteractions(request, response, authException);
    }
}
