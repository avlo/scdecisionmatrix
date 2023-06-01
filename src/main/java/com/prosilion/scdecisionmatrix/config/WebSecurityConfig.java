package com.prosilion.scdecisionmatrix.config;


import com.prosilion.scdecisionmatrix.security.entity.AuthUserDetails;
import com.prosilion.scdecisionmatrix.security.entity.AuthUserDetailsImpl;
import com.prosilion.scdecisionmatrix.security.service.AuthUserDetailServiceImpl;
import com.prosilion.scdecisionmatrix.security.service.AuthUserDetailsService;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
        .and()
        .authorizeHttpRequests()
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

  /**
   * Customizable bean extending JdbcUserDetailsManager for Spring Security 6.0.3
   *
   * @param dataSource
   * @return AuthUserDetailsService is a customizable UserDetailsService
   */
  @Bean
  public AuthUserDetailsService authUserDetailsService(DataSource dataSource) {
    UserDetails userDetails = User.withUsername("user").password(passwordEncoder().encode("userpass"))
        .roles("USER").build();
    AuthUserDetails userUser = new AuthUserDetailsImpl(userDetails);
    AuthUserDetailsService users = new AuthUserDetailServiceImpl(dataSource);
    users.createUser(userUser);
    return users;
  }

  @Bean
  WebSecurityCustomizer webSecurityCustomizer() {
    return web -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/h2-console/**"));
  }
}
