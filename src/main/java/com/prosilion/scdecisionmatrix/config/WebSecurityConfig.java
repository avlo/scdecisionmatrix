package com.prosilion.scdecisionmatrix.config;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

import com.prosilion.scdecisionmatrix.security.AuthUserDetailServiceImpl;
import com.prosilion.scdecisionmatrix.security.AuthUserDetails;
import com.prosilion.scdecisionmatrix.security.AuthUserDetailsImpl;
import com.prosilion.scdecisionmatrix.security.AuthUserDetailsService;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
  @Bean
  DataSource embeddedDataSource() {
    return new EmbeddedDatabaseBuilder()
        .setType(H2)
        .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
        .build();
  }

  // TODO: add external datasource next POC
//  @Bean
//  DataSource externalDataSource() {
//    return new EmbeddedDatabaseBuilder()
//        .setType(H2)
//        .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
//        .build();
//  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests()
        .requestMatchers("/**")
        .hasRole("USER")
        .and()
        .formLogin()
        .successForwardUrl("/loginuser");
    return http.build();

    // TODO: attempt to get below variation working at some point, has interesting handling possiblities
    //        http.formLogin()
    //            .loginPage("/login")
    //            .loginProcessingUrl("/perform_login")
    //            .defaultSuccessUrl("/homepage.html", true)
    //            .failureUrl("/login.html?error=true");
    //        return http.build();

    // TODO: same as above w/ lambda
    //    http.formLogin(
    //            form ->
    //                form.loginPage("/login")
    //                            .loginProcessingUrl("/perform_login")
    //                    .permitAll())
    //        ////                    .defaultSuccessUrl("/homepage.html", true))
    //        .cors()
    //        .and()
    //        .csrf()
    //        .disable()
    //        .authorizeHttpRequests()
    //        .requestMatchers(WHITE_LIST_URLS)
    //        .permitAll();
    //        return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /*************************************************
   * keep this method handy/commented for reference/convenience testing
  @Bean
  public UserDetailsService inMemoryUserDetailsService() {
    UserDetails user =
        User.withUsername("user").password(passwordEncoder().encode("userpass")).roles("USER").build();
    return new InMemoryUserDetailsManager(user);
  }
   *************************************************/

  @Bean
  public AuthUserDetailsService authUserDetailsService() {
    AuthUserDetails adminUser = new AuthUserDetailsImpl(User.withUsername("admin").password(passwordEncoder().encode("adminpass")).roles("ADMIN").build());
    AuthUserDetails staffUser = new AuthUserDetailsImpl(User.withUsername("staff").password(passwordEncoder().encode("staffpass")).roles("STAFF").build());
    AuthUserDetails userUser = new AuthUserDetailsImpl(User.withUsername("user").password(passwordEncoder().encode("userpass")).roles("USER").build());
    AuthUserDetails customUser = new AuthUserDetailsImpl(User.withUsername("custom").password(passwordEncoder().encode("custompass")).roles("CUSTOM").build());
    AuthUserDetailsService users = new AuthUserDetailServiceImpl(embeddedDataSource());
    users.createUser(adminUser);
    users.createUser(staffUser);
    users.createUser(userUser);
    users.createUser(customUser);
    return users;
  }

  @Bean
  WebSecurityCustomizer webSecurityCustomizer() {
    return web -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/h2-console/**"));
  }
}
