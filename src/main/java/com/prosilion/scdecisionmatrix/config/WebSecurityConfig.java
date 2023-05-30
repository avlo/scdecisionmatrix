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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  /**
   * TODO: add external datasource once auth/auth process properly works
   */
  @Bean
  DataSource dataSource() {
    return new EmbeddedDatabaseBuilder()
        .setType(H2)
        .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
        .build();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests()
        .requestMatchers("/**")
        .hasRole("USER")
        .and()
        .formLogin()
        .successForwardUrl("/loginuser");
    return http.build();

    //        http.formLogin()
    //            .loginPage("/login")
    //            .loginProcessingUrl("/perform_login")
    //            .defaultSuccessUrl("/homepage.html", true)
    //            .failureUrl("/login.html?error=true");
    //        return http.build();

    //    http.authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
    //        .httpBasic(withDefaults());
    //    return http.build();

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

    //    http.userDetailsService(getUserDetailsService())
    //        .authorizeRequests()
    //        .anyRequest()
    //        .authenticated()
    //        .and()
    //        .formLogin()
    //        .loginPage("/login")
    //        .permitAll()
    //        .successForwardUrl("/index")
    ////        .and()
    ////        .logout()
    //        .permitAll();
    ////        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
    ////        .logoutSuccessUrl("/login");
    //    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /*************************************************
   * KEEP FOR REFERENCE!!! internal memory user detail manager
   *************************************************/
//  @Bean
//  public UserDetailsService inMemoryUserDetailsService() {
//    UserDetails user =
//        User.withUsername("user").password(passwordEncoder().encode("pass")).roles("USER").build();
//    return new InMemoryUserDetailsManager(user);
//  }

  @Bean
  public AuthUserDetailsService authUserDetailsService() {
    AuthUserDetails user = new AuthUserDetailsImpl(
        User.withUsername("user").password(passwordEncoder().encode("pass")).roles("USER").build());
    AuthUserDetailsService users = new AuthUserDetailServiceImpl(dataSource());
    users.createUser(user);
    return users;
  }

  @Bean
  WebSecurityCustomizer webSecurityCustomizer() {
    return web -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/h2-console/**"));
  }
}
