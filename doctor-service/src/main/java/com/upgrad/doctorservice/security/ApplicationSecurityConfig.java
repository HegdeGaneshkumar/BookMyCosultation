package com.upgrad.doctorservice.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers(HttpMethod.POST, "/doctors").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/doctors/{doctorId}/document").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/doctors/{doctorId}/approve").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/doctors/{doctorId}/reject").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/doctors**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().permitAll()
                )
                .httpBasic();
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("Password@123")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}
