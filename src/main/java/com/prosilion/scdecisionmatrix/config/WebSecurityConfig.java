package com.prosilion.scdecisionmatrix.config;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

import com.prosilion.scdecisionmatrix.security.AuthUserDetailService;
import com.prosilion.scdecisionmatrix.security.AuthUserDetailServiceImpl;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  private static final AntPathRequestMatcher[] WHITE_LIST_URLS = {
    new AntPathRequestMatcher("/user/**"), new AntPathRequestMatcher("/contract/**"),
    // new AntPathRequestMatcher("/h2-console/**"),
  };

  @Bean
  DataSource dataSource() {
    return new EmbeddedDatabaseBuilder()
        .setType(H2)
        .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
        .build();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.cors()
        .and()
        .csrf()
        .disable()
        .authorizeHttpRequests()
        .requestMatchers(WHITE_LIST_URLS)
        .permitAll();
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(11);
  }

  @Bean
  AuthUserDetailService getUserDetailsService() {
    return new AuthUserDetailServiceImpl();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(passwordEncoder());
    provider.setUserDetailsService(getUserDetailsService());
    return provider;
  }

  @Bean
  WebSecurityCustomizer webSecurityCustomizer() {
    return web -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/h2-console/**"));
  }
}
