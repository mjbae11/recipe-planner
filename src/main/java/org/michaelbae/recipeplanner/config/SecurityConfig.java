package org.michaelbae.recipeplanner.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /** allows APIs to be public and outside services can call them.
     * (used during development to allow postman to test APIs
     * @param http
     * @return Security filter chain
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for development purposes
            .authorizeHttpRequests(auth -> auth
                    .anyRequest().permitAll() // Allow all requests without authentication
            );
        return http.build();
    }
}