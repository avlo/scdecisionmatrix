package com.prosilion.scdecisionmatrix.security;

import com.prosilion.scdecisionmatrix.entity.User;
import com.prosilion.scdecisionmatrix.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthUserDetailServiceImpl implements AuthUserDetailService {

  @Autowired private UserRepository userRepository;

  @Override
  public AuthUserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.getUserByUsername(username);

    if (user == null) {
      throw new UsernameNotFoundException("Could not find user");
    }

    return new AuthUserDetailsImpl(user);
  }

  @Override
  public User loadUserById(Long id) throws UsernameNotFoundException {
    return userRepository.getUserById(id);
  }

  public User save(User user) throws UsernameNotFoundException {
    return userRepository.saveAndFlush(user);
  }
}
