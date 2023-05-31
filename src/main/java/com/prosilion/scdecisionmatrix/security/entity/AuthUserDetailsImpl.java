package com.prosilion.scdecisionmatrix.security.entity;

import java.util.Collection;
import lombok.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthUserDetailsImpl implements AuthUserDetails {

  //  private boolean enabled;

  //	private Long satoshis;
  //	private Long reputation; // (f(contract_state(payer_state, payee_state, payout_time))

//  @OneToOne(mappedBy = "user", optional = true)
//  private Contract contract;

  final private UserDetails user;

  public AuthUserDetailsImpl(@NonNull UserDetails user) {
    this.user = user;
  }

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
