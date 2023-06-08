package com.prosilion.scdecisionmatrix.security.entity;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthUserDetails extends UserDetails {
  UserDetails getUser();
}
