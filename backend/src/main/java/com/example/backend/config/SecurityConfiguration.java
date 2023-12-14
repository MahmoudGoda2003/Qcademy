package com.example.backend.config;

import com.example.backend.person.model.Role;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    @Autowired
    private final AuthenticationEntryPoint jwtAuthenticationEntryPoint;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
//                .cors(cors -> cors)
                .authorizeHttpRequests(request -> {
                    request.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
                    request.requestMatchers("/person/**").permitAll();
                    request.requestMatchers("/admin/**")
                            .hasAnyAuthority(Role.ADMIN.name());
                    request.requestMatchers("/teacher/**")
                            .hasAnyAuthority(Role.TEACHER.name());
                    request.requestMatchers("/student/**")
                            .hasAnyAuthority(Role.STUDENT.name());
                    request.anyRequest().authenticated();
                })
                .exceptionHandling((exception)-> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint).accessDeniedPage("/error/accedd-denied"))
                .sessionManagement(sess->sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}