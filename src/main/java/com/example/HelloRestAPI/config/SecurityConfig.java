package com.example.HelloRestAPI.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for REST API
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/api/**").permitAll() // Allow all API endpoints
                .requestMatchers("/actuator/**").permitAll() // Allow actuator endpoints if added later
                .anyRequest().authenticated()
            );
        
        return http.build();
    }
}
