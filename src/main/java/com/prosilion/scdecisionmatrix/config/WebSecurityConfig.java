package com.prosilion.scdecisionmatrix.config;

import com.prosilion.scdecisionmatrix.security.AuthUserDetailService;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig {

//  private final AuthUserDetailService authUserDetailService;
//
//  @Autowired
//  public WebSecurityConfig(AuthUserDetailService authUserDetailService) {
//    this.authUserDetailService = authUserDetailService;
//  }
private static final AntPathRequestMatcher[] WHITE_LIST_URLS = {
    new AntPathRequestMatcher("/user/**"),
    new AntPathRequestMatcher("/contract/**"),
    // new AntPathRequestMatcher("/h2-console/**"),
};
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .cors()
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
  public DataSource dataSource() {
    return new EmbeddedDatabaseBuilder()
        .setType(EmbeddedDatabaseType.H2)
        .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
        .build();
  }
  //
  @Bean
  public UserDetailsManager users(DataSource dataSource) {
    UserDetails user =
        //        User.withDefaultPasswordEncoder()
        User.builder().username("user").password("password").roles("USER").build();
    JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
    users.createUser(user);
    return users;
  }

  @Bean
  WebSecurityCustomizer webSecurityCustomizer() {
    return web -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/h2-console/**"));
  }
}
