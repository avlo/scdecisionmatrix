package com.prosilion.scdecisionmatrix.security.service;

import com.prosilion.scdecisionmatrix.dto.AppUserDto;
import com.prosilion.scdecisionmatrix.security.entity.AuthUserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Customizable service for extendable/customizable user
 */
public interface AuthUserDetailsService extends UserDetailsService {
	void createUser(AppUserDto appUserDto);
	AuthUserDetails loadUserByUsername(String username);
	AuthUserDetails loadUserByUserDto(AppUserDto appUserDto);
}
