package com.prosilion.scdecisionmatrix.security;

import javax.sql.DataSource;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

//@Transactional
public class AuthUserDetailServiceImpl extends JdbcUserDetailsManager implements AuthUserDetailsService {

  public AuthUserDetailServiceImpl(DataSource dataSource) {
    super(dataSource);
  }

  @Override
  public void createUser(AuthUserDetails authUserDetails) {
    super.createUser(authUserDetails);
  }

  @Override
  public AuthUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return new AuthUserDetailsImpl(super.loadUserByUsername(username));
  }
//
//  public User save(User user) throws UsernameNotFoundException {
//    return userRepository.saveAndFlush(user);
//  }
}
