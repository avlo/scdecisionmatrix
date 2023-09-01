package com.prosilion.scdecisionmatrix.config;

import com.prosilion.scdecisionmatrix.repository.security.AppUserAuthUserRepository;
import com.prosilion.scdecisionmatrix.service.AppUserAuthUserService;
import com.prosilion.scdecisionmatrix.service.AppUserService;
import com.prosilion.scdecisionmatrix.service.security.AuthUserDetailServiceImpl;
import com.prosilion.scdecisionmatrix.service.security.AuthUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Bean
    MvcRequestMatcher.Builder builder() {
        return new MvcRequestMatcher.Builder(new HandlerMappingIntrospector());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc)
            throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(mvc.pattern("/register/**"))
                        .permitAll()
                        .requestMatchers(mvc.pattern("/index"))
                        .permitAll()
                        .requestMatchers(mvc.pattern("/users/**"))
                        .permitAll()
                        .requestMatchers(mvc.pattern("/contract/**"))
                        .hasRole("USER"))
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/loginuser")
                        .defaultSuccessUrl("/users")
                        .permitAll())
                .logout(logout -> logout
                        .logoutRequestMatcher(mvc.pattern("/logout")).permitAll());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AppUserAuthUserService appUserAuthUserService(
            AuthUserDetailsService authUserDetailsService,
            AppUserService appUserService,
            AppUserAuthUserRepository appUserAuthUserRepository) {
        return new AppUserAuthUserService(
                authUserDetailsService, appUserService, appUserAuthUserRepository);
    }

    @Bean
    public AuthUserDetailsService authUserDetailsService(DataSource dataSource) {
        return new AuthUserDetailServiceImpl(dataSource, passwordEncoder());
    }

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/h2-console/**"));
    }
}
