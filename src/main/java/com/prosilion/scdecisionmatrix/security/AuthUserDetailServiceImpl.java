package com.prosilion.scdecisionmatrix.security;

import javax.sql.DataSource;
import org.springframework.security.core.userdetails.UserDetails;
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
    UserDetails user = super.loadUserByUsername(username);

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
