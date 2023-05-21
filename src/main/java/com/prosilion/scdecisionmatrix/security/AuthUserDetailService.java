package com.prosilion.scdecisionmatrix.security;

import com.prosilion.scdecisionmatrix.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthUserDetailService extends UserDetailsService {
	User loadUserById(Long id);
	User save(User user);
}
