package com.prosilion.scdecisionmatrix.security.entity;

import com.prosilion.scdecisionmatrix.entity.ContractUser;
import java.util.Collection;
import lombok.NonNull;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * Extendable/customizable user + Spring Security Authentication and Authorization
 */
@Component
@Scope("session")
public class AuthUserDetailsImpl implements AuthUserDetails {

  final private UserDetails user;
  final private ContractUser contractUser;

  public AuthUserDetailsImpl(@NonNull UserDetails user) {
    this.user = user;
    this.contractUser = new ContractUser();
  }

  @Override
  public UserDetails getUser() {
    return user;
  }

  @Override
  public ContractUser getContractUser() { return contractUser; }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return user.getAuthorities();
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public String toString() {
    return "AuthUserDetailsImpl{" +
        "user=" + user +
        '}';
  }
}
