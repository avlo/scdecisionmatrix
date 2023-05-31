package com.prosilion.scdecisionmatrix.security;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthUserDetailsService extends UserDetailsService {
	void createUser(AuthUserDetails authUserDetails);
	AuthUserDetails loadUserByUsername(String username);
}
