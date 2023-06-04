package com.prosilion.scdecisionmatrix.config;


import com.prosilion.scdecisionmatrix.repository.ContractuserAuthuserRepository;
import com.prosilion.scdecisionmatrix.security.service.AuthUserDetailServiceImpl;
import com.prosilion.scdecisionmatrix.security.service.AuthUserDetailsService;
import com.prosilion.scdecisionmatrix.service.ContractUserAuthUserService;
import com.prosilion.scdecisionmatrix.service.ContractUserService;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
  private static Logger LOGGER = LoggerFactory.getLogger(WebSecurityConfig.class);
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .authorizeHttpRequests((authorize) ->
            authorize.requestMatchers("/login/**").permitAll()
//                .requestMatchers("/login/**").permitAll()
                .requestMatchers("/contract/**").hasRole("USER")
        ).formLogin(
            form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/contract/display")
                .permitAll()
        ).logout(
            logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .permitAll()
        );
    return http.build();
//    http.sessionManagement()
//        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//        .and()
//        .authorizeHttpRequests()
//        .requestMatchers("/**")
//        .hasRole("USER")
//        .and()
//        .formLogin()
//        .successForwardUrl("/loginuser");
//    return http.build();

    /*
    .authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/registration").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
     */


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

  @Bean
  public ContractUserAuthUserService contractUserAuthUserService(AuthUserDetailsService authUserDetailsService,
      ContractUserService contractUserService, ContractuserAuthuserRepository contractuserAuthuserRepository) {
    LOGGER.info("WebSecurityConfig 000000000000000000000");
    LOGGER.info("WebSecurityConfig 000000000000000000000");
    return new ContractUserAuthUserService(authUserDetailsService, contractUserService, contractuserAuthuserRepository);
  }
  @Bean
  public AuthUserDetailsService authUserDetailsService(DataSource dataSource) {
//    UserDetails userDetails = User.withUsername("user").password(passwordEncoder().encode("userpass"))
//        .roles("USER").build();
//    AuthUserDetails userUser = new AuthUserDetailsImpl(userDetails);
    LOGGER.info("WebSecurityConfig 111111111111111111111");
    LOGGER.info("WebSecurityConfig 111111111111111111111");
    AuthUserDetailsService authUserDetailsService = new AuthUserDetailServiceImpl(dataSource);
//    authUserDetailsService.createUser(userUser);
    return authUserDetailsService;
  }

  @Bean
  WebSecurityCustomizer webSecurityCustomizer() {
    return web -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/h2-console/**"));
  }
}
