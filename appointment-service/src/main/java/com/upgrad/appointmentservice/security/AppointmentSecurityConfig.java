package com.upgrad.appointmentservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AppointmentSecurityConfig {

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    .csrf().disable();
            http
                    .authorizeHttpRequests((authz) -> authz
                            .requestMatchers(HttpMethod.GET, "/users**").hasAnyRole("ADMIN", "USER")
                            .requestMatchers(HttpMethod.GET, "/doctor**").hasAnyRole("ADMIN", "USER")
                            .requestMatchers(HttpMethod.POST,"/doctor**").hasAnyRole("ADMIN", "USER")
                            .requestMatchers(HttpMethod.GET, "/appointments**").hasAnyRole("ADMIN", "USER")
                            .requestMatchers(HttpMethod.POST,"/appointments**").hasAnyRole("ADMIN", "USER")
                            .requestMatchers(HttpMethod.POST,"/prescriptions**").hasRole("USER")
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
