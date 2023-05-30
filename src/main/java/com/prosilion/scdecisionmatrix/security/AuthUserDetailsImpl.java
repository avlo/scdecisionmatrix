package com.prosilion.scdecisionmatrix.security;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthUserDetailsImpl implements AuthUserDetails {

  //  private boolean enabled;

  //	private Long satoshis;
  //	private Long reputation; // (f(contract_state(payer_state, payee_state, payout_time))

//  @OneToOne(mappedBy = "user", optional = true)
//  private Contract contract;

  final private UserDetails user;

  public AuthUserDetailsImpl(UserDetails user) {
    this.user = user;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    System.out.println("AuthUserDetailsImpl 00000000000000");
    System.out.println("AuthUserDetailsImpl 00000000000000");
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
}
