package com.prosilion.scdecisionmatrix.service;

import com.prosilion.scdecisionmatrix.entity.AppUser;
import com.prosilion.scdecisionmatrix.dto.AppUserDto;
import com.prosilion.scdecisionmatrix.entity.AppUserAuthUser;
import com.prosilion.scdecisionmatrix.repository.AppUserAuthUserRepository;
import com.prosilion.scdecisionmatrix.security.entity.AuthUserDetails;
import com.prosilion.scdecisionmatrix.security.service.AuthUserDetailsService;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

//@Service // this is a bean
public class AppUserAuthUserService {
	private static Logger LOGGER = LoggerFactory.getLogger(AppUserAuthUserService.class);
	private final AppUserAuthUserRepository appUserAuthUserRepository;
	private final AuthUserDetailsService authUserDetailsService;
	private final AppUserService appUserService;

	public AppUserAuthUserService(AuthUserDetailsService authUserDetailsService,
			AppUserService appUserService, AppUserAuthUserRepository appUserAuthUserRepository) {
		this.authUserDetailsService = authUserDetailsService;
		this.appUserService = appUserService;
		this.appUserAuthUserRepository = appUserAuthUserRepository;
	}

	@Transactional
	public AppUserAuthUser createUser(@NonNull AppUserDto appUserDto) {
		AuthUserDetails savedAuthUserDetails = authUserDetailsService.loadUserByUserDto(appUserDto);
		AppUser appUser = appUserService.save(new AppUser());
		AppUserAuthUser appUserAuthUser = new AppUserAuthUser(appUser.getId(), savedAuthUserDetails.getUsername());
		return appUserAuthUserRepository.saveAndFlush(appUserAuthUser);
	}

	public AppUserAuthUser getAppUserAuthUser(@NonNull AppUser appUser) {
		return appUserAuthUserRepository.findByAppUserId(appUser.getId()).get();
	}

	public AppUserAuthUser getAppUserAuthUser(@NonNull AuthUserDetails authUserDetails) {
		return appUserAuthUserRepository.findByAuthUserName(authUserDetails.getUsername()).get();
	}
}
