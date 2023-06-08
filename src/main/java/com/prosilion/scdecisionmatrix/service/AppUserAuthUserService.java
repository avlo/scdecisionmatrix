package com.prosilion.scdecisionmatrix.service;

import com.prosilion.scdecisionmatrix.entity.AppUser;
import com.prosilion.scdecisionmatrix.dto.AppUserDto;
import com.prosilion.scdecisionmatrix.entity.AppuserAuthuser;
import com.prosilion.scdecisionmatrix.repository.AppuserAuthuserRepository;
import com.prosilion.scdecisionmatrix.security.entity.AuthUserDetails;
import com.prosilion.scdecisionmatrix.security.service.AuthUserDetailsService;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

//@Service // this is a bean
public class AppUserAuthUserService {
	private static Logger LOGGER = LoggerFactory.getLogger(AppUserAuthUserService.class);
	private final AppuserAuthuserRepository appuserAuthuserRepository;
	private final AuthUserDetailsService authUserDetailsService;
	private final AppUserService appUserService;

	public AppUserAuthUserService(AuthUserDetailsService authUserDetailsService,
			AppUserService appUserService, AppuserAuthuserRepository appuserAuthuserRepository) {
		this.authUserDetailsService = authUserDetailsService;
		this.appUserService = appUserService;
		this.appuserAuthuserRepository = appuserAuthuserRepository;
	}

	@Transactional
	public AppuserAuthuser createUser(@NonNull AppUserDto appUserDto) {
		AuthUserDetails savedAuthUserDetails = authUserDetailsService.loadUserByUserDto(appUserDto);
		AppUser appUser = appUserService.save(new AppUser());
		AppuserAuthuser appuserAuthuser = new AppuserAuthuser(appUser.getId(), savedAuthUserDetails.getUsername());
		return appuserAuthuserRepository.saveAndFlush(appuserAuthuser);
	}

	public AppuserAuthuser getAppuserAuthuser(@NonNull AppUser appUser) {
		return appuserAuthuserRepository.findByAppuserId(appUser.getId()).get();
	}

	public AppuserAuthuser getAppuserAuthuser(@NonNull AuthUserDetails authUserDetails) {
		return appuserAuthuserRepository.findByAuthuserName(authUserDetails.getUsername()).get();
	}
}
