package com.example.backend.PersonTests.Security;

import com.example.backend.config.JwtAuthenticationFilter;
import com.example.backend.person.dto.PersonUserDetails;
import com.example.backend.person.model.Role;
import com.example.backend.person.service.PersonService;
import com.example.backend.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@AutoConfigureMockMvc
public class Filter {
    @Mock
    private JwtService jwtService;

    @Mock
    private PersonService personService;

    @InjectMocks
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDoFilterInternal() throws ServletException, IOException {
        // Mock HttpServletRequest, HttpServletResponse, and FilterChain
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        // Mock JwtService behavior
        String mockJwt = "mockJwt";
        when(request.getCookies()).thenReturn(new Cookie[]{new Cookie("qcademy", mockJwt)});
        when(jwtService.extractUserId(mockJwt)).thenReturn(123L);
        when(personService.getUserRole(123L)).thenReturn(Role.STUDENT);
        when(jwtService.isTokenValid(mockJwt, Role.STUDENT)).thenReturn(true);

        // Call the doFilterInternal method
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);
        assertEquals(Role.STUDENT.name(), SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0].toString());
        assertEquals("123", SecurityContextHolder.getContext().getAuthentication().getName());

        // Verify that the authentication was set in the SecurityContextHolder
        UserDetails personUserDetails = new PersonUserDetails(123L, Role.STUDENT);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                personUserDetails,
                null,
                personUserDetails.getAuthorities()
        );
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        verify(personService, times(1)).getUserRole(123L);
        verify(jwtService, times(1)).isTokenValid(mockJwt, Role.STUDENT);
        verify(filterChain, times(1)).doFilter(request, response);
        // Verify that no other interactions occurred
        SecurityContextHolder.clearContext();
//        verifyNoMoreInteractions(request, response, filterChain);
    }
}
