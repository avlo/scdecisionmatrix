package com.prosilion.scdecisionmatrix.security;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthUserDetails extends UserDetails {

  @Override
  Collection<? extends GrantedAuthority> getAuthorities();

  @Override
  String getPassword();

  @Override
  String getUsername();

  @Override
  boolean isAccountNonExpired();

  @Override
  boolean isAccountNonLocked();

  @Override
  boolean isCredentialsNonExpired();

  @Override
  boolean isEnabled();
}
