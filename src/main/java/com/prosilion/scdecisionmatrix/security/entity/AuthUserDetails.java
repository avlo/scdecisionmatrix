package com.prosilion.scdecisionmatrix.security.entity;

import com.prosilion.scdecisionmatrix.entity.ContractUser;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Extendable/customizable user + Spring Security Authentication and Authorization
 */
public interface AuthUserDetails extends UserDetails {
  UserDetails getUser();
}
