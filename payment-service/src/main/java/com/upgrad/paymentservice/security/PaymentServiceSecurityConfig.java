package com.upgrad.paymentservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class PaymentServiceSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable();
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JWTAuthenticationFilter(http.getSharedObject(AuthenticationManager.class)))
                .addFilterAfter(new JWTTokenVerifier(), JWTAuthenticationFilter.class)
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers(HttpMethod.POST,"/payments**").hasAuthority("USER")
                        .anyRequest().permitAll()
                );
        return http.build();
    }
}
