package com.typefigth.credentials_api.infrastructure.config;


import com.typefigth.credentials_api.infrastructure.security.filter.JwtAuthenticationFilter;
import com.typefigth.credentials_api.infrastructure.security.filter.JwtValidationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;


    public SpringSecurityConfig(AuthenticationConfiguration authenticationConfiguration) {
        this.authenticationConfiguration = authenticationConfiguration;
    }

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests((auth) -> auth
                        .requestMatchers(HttpMethod.POST, "/auth/signin").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/signup").permitAll()
                        // .requestMatchers(HttpMethod.GET, "/api/users").hasRole("ADMIN")
                        // .requestMatchers(HttpMethod.POST, "/api/users").hasRole("ADMIN")
                        // .requestMatchers(HttpMethod.GET, "/api/products", "/api/products/{id}").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated())
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtValidationFilter(authenticationManager()))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

}
