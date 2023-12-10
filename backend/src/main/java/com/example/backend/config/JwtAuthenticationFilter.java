package com.example.backend.config;

import com.example.backend.person.dto.PersonUserDetails;
import com.example.backend.person.model.Role;
import com.example.backend.person.service.PersonService;
import com.example.backend.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final PersonService personService;
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final Cookie[] cookies = request.getCookies();
        if(cookies == null){
            filterChain.doFilter(request, response);
            return;
        }
        String jwt = null;
        for (Cookie cookie : cookies) {
            if ("qcademy".equals(cookie.getName())) {
                jwt = cookie.getValue();
            }
        }
        if(jwt == null){
            filterChain.doFilter(request, response);
            return;
        }
        Long userId = jwtService.extractUserId(jwt);
        Role actualRole = personService.getUserRole(userId);
        if((actualRole != null)
                && (SecurityContextHolder.getContext().getAuthentication() == null)
                && (jwtService.isTokenValid(jwt, actualRole)))
        {
            UserDetails personUserDetails = new PersonUserDetails(userId, actualRole);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    personUserDetails,
                    null,
                    personUserDetails.getAuthorities()
            );
            authToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        filterChain.doFilter(request, response);
    }
}
