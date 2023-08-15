package com.upgrad.doctorservice.security;
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

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ApplicationSecurityConfig  {

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
                        .requestMatchers(HttpMethod.POST, "/doctors").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/doctors/{doctorId}/document").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/doctors/{doctorId}/approve").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/doctors/{doctorId}/reject").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/doctors**").hasAnyAuthority("ADMIN", "USER")
                        .anyRequest().permitAll()
                );
        return http.build();
    }

}
