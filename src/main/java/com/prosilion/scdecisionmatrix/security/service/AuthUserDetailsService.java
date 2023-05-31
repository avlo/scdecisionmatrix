package com.prosilion.scdecisionmatrix.security.service;

import com.prosilion.scdecisionmatrix.security.entity.AuthUserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Customizable service for extendable/customizable user
 */
public interface AuthUserDetailsService extends UserDetailsService {
	void createUser(AuthUserDetails authUserDetails);
	AuthUserDetails loadUserByUsername(String username);
}
