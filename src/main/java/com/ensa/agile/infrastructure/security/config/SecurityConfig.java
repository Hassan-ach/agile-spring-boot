package com.ensa.agile.infrastructure.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ensa.agile.infrastructure.security.fillter.JwtFilter;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

        private final JwtFilter jwtFilter;
        private static final String[] PUBLIC_URLS = {
                        "/api/auth/login",
                        "/api/auth/register",
                        "/api/auth/refresh",
                        "/error",
        };

        @Bean
        public SecurityFilterChain filterChain(final HttpSecurity http) {
                return http.csrf(AbstractHttpConfigurer::disable)
                                .sessionManagement(
                                                session -> session
                                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authorizeHttpRequests(
                                                auth -> auth
                                                                .requestMatchers(PUBLIC_URLS)
                                                                .permitAll()
                                                                .anyRequest()
                                                                .authenticated())
                                .addFilterBefore(this.jwtFilter, UsernamePasswordAuthenticationFilter.class)
                                .build();

        }

}
