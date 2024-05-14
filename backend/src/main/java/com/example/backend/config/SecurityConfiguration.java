package com.example.backend.config;

import com.example.backend.person.model.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationEntryPoint jwtAuthenticationEntryPoint;

    public SecurityConfiguration(JwtAuthenticationFilter jwtAuthFilter, AuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(request -> {
                    request.requestMatchers("/person/**").permitAll();
                    request.requestMatchers("/admin/**")
                            .hasAnyAuthority(Role.ADMIN.name());
                    request.requestMatchers("/teacher/**")
                            .hasAnyAuthority(Role.TEACHER.name());
                    request.requestMatchers("/student/**")
                            .hasAnyAuthority(Role.STUDENT.name());
                    request.anyRequest().authenticated();
//                    request.anyRequest().permitAll();
                })
                .exceptionHandling((exception)-> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint).accessDeniedPage("/error/accedd-denied"))
                .sessionManagement(sess->sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}