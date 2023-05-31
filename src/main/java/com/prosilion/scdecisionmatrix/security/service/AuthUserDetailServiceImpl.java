package com.prosilion.scdecisionmatrix.security.service;

import com.prosilion.scdecisionmatrix.security.entity.AuthUserDetails;
import com.prosilion.scdecisionmatrix.security.entity.AuthUserDetailsImpl;
import javax.sql.DataSource;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

/**
 * Customizable service for extendable/customizable user that works with
 * Spring Security 6.0.3 via extending JdbcUserDetailsManager
 */
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
