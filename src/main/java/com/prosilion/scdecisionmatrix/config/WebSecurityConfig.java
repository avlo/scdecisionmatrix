package com.prosilion.scdecisionmatrix.config;

import edu.mayo.lpea.cad.cadence.web.controller.AuthController;
import edu.mayo.lpea.cad.cadence.web.controller.UsersController;
import edu.mayo.lpea.cad.cadence.web.security.jpa.config.JpaSecurityConfig;
import edu.mayo.lpea.cad.cadence.web.security.jpa.controller.JpaAuthController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;

@Configuration
@EnableWebSecurity
@Import({JpaSecurityConfig.class, UsersController.class, JpaAuthController.class, AuthController.class})
public class WebSecurityConfig {
  private static final Logger LOGGER = LoggerFactory.getLogger(WebSecurityConfig.class);

//  @Bean
//  public PasswordEncoder passwordEncoder() {
//    return new BCryptPasswordEncoder();
//  }

//  @Bean
//  MvcRequestMatcher.Builder builder() {
//    return new MvcRequestMatcher.Builder(new HandlerMappingIntrospector());
//  }

  @Bean
  public SecurityFilterChain scdFilterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
    http.authorizeHttpRequests(authorize -> authorize
        .requestMatchers(
            mvc.pattern("/contract/**")).hasRole("USER")
    );
    return http.build();
  }
}
