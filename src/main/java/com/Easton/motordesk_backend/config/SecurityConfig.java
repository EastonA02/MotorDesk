package com.Easton.motordesk_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//mark class as source of bean(object) definitions
// bean = object who's life-cycle is managed by spring
@Configuration
public class SecurityConfig {

    /*@Bean
    Tells method to register returned object as a bean
    */

    @Bean
    //PasswordEncoder provides methods such as:
    // encode: hash password
    // matches: check password to db hash
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        //define authorization rules
        http
                //prevent csfr block since testing in postman
                .csrf(csfr -> csfr.disable())

                .authorizeHttpRequests(auth -> auth

                //allow anyone to access these endpoints
                .requestMatchers(
                        HttpMethod.POST, "/api/motordesk/user").permitAll()

                 //any requests other than those listed above require access
                .anyRequest().authenticated());

        return http.build();
    }

}
