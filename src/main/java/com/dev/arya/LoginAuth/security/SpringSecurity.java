package com.dev.arya.LoginAuth.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurity {


    @Bean
    public SecurityFilterChain filteringCriteria(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // ✅ new recommended way
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll()
                );
        return http.build();
    }
    //Objects that handle what api endpoints shpuld be authenticated and what not
}
