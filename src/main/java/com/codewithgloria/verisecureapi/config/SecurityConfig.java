package com.codewithgloria.verisecureapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // disable CSRF so Postman can send POST requests
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/verisecure/registration", "/verisecure/login").permitAll()
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}
