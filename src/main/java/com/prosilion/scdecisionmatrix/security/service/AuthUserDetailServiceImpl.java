package com.prosilion.scdecisionmatrix.security.service;

import com.prosilion.scdecisionmatrix.entity.UserDto;
import com.prosilion.scdecisionmatrix.security.entity.AuthUserDetails;
import com.prosilion.scdecisionmatrix.security.entity.AuthUserDetailsImpl;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.transaction.annotation.Transactional;

/**
 * Customizable service for extendable/customizable user that works with
 * Spring Security 6.0.3 via extending JdbcUserDetailsManager
 */
//@Transactional
public class AuthUserDetailServiceImpl extends JdbcUserDetailsManager implements AuthUserDetailsService {
  private static Logger LOGGER = LoggerFactory.getLogger(AuthUserDetailServiceImpl.class);
  private final PasswordEncoder passwordEncoder;

  public AuthUserDetailServiceImpl(DataSource dataSource, PasswordEncoder passwordEncoder) {
    super(dataSource);
    this.passwordEncoder = passwordEncoder;
  }

  @Transactional
  @Override
  public void createUser(UserDto userDto) {
    UserDetails userDetails = User.withUsername(userDto.getFirstName()).password(passwordEncoder.encode(userDto.getPassword())).roles("USER").build();
    AuthUserDetails authUserDetails = new AuthUserDetailsImpl(userDetails);
    super.createUser(authUserDetails);
  }

  @Override
  public AuthUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return new AuthUserDetailsImpl(super.loadUserByUsername(username));
  }

  @Override
  public AuthUserDetails loadUserByUserDto(UserDto userDto) {
    try {
      AuthUserDetails authUserDetails = loadUserByUsername(userDto.getFirstName());
      LOGGER.info("User found");
      return authUserDetails;
    } catch (UsernameNotFoundException u) {
      LOGGER.info("User not found, try to create new user");
      createUser(userDto);
      AuthUserDetails authUserDetails= loadUserByUsername(userDto.getFirstName());
      LOGGER.info("Created new user");
      return authUserDetails;
    }
  }
//
//  public User save(User user) throws UsernameNotFoundException {
//    return userRepository.saveAndFlush(user);
//  }
}
