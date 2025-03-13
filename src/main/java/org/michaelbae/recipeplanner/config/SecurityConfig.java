package org.michaelbae.recipeplanner.config;


import org.michaelbae.recipeplanner.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig
{

    private final UserDetailsServiceImpl userDetailsServiceImpl; // Inject your custom UserDetailsService

    @Autowired
    public SecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http

//                .authorizeHttpRequests(auth -> auth
//                    .requestMatchers("/","/login", "/signup", "/css/**", "/js/**", "/assets/**").permitAll()  // Allow Basic pages without authentication
//                    .anyRequest().authenticated()  // Require authentication for all other pages
//                )
//                .formLogin(form -> form
//                    .loginPage("/login")  // Custom login page
//                    .defaultSuccessUrl("/recipes", true) // redirect after successful
//                    .permitAll()  // Allow everyone to access the login page
//                )
//                .logout(logout -> logout
//                    .permitAll()  // Allow everyone to log out
//                );
//        temporary turn off of spring security
        .csrf(csrf -> csrf.disable()) // Disable CSRF for testing (optional)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Allow all requests without authentication
                );
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
                .userDetailsService(userDetailsServiceImpl)
                .passwordEncoder(passwordEncoder());

        return authenticationManagerBuilder.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCrypt password encoder for securely storing passwords
        return new BCryptPasswordEncoder();
    }
}