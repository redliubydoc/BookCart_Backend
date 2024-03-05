package com.raj.ms.identity.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain getSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {

    httpSecurity
      .authorizeHttpRequests(request -> request.anyRequest().permitAll());

    return httpSecurity.build();
  }

}
