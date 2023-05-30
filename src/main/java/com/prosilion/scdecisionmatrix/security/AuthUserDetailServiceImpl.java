package com.prosilion.scdecisionmatrix.security;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Transactional
public class AuthUserDetailServiceImpl implements AuthUserDetailsService {

  private final JdbcUserDetailsManager jdbcUserDetailsManager;

  @Autowired
  public AuthUserDetailServiceImpl(JdbcUserDetailsManager jdbcUserDetailsManager) {
    this.jdbcUserDetailsManager = jdbcUserDetailsManager;
  }

  @Override
  public void createUser(UserDetails authUserDetails) {
    jdbcUserDetailsManager.createUser(authUserDetails);
  }
  @Override
  public AuthUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserDetails user = jdbcUserDetailsManager.loadUserByUsername(username);

    if (user == null) {
      throw new UsernameNotFoundException("Could not find user");
    }

    return new AuthUserDetailsImpl(user);
  }
//
//  public User save(User user) throws UsernameNotFoundException {
//    return userRepository.saveAndFlush(user);
//  }
}
