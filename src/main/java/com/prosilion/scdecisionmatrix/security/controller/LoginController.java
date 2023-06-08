package com.prosilion.scdecisionmatrix.security.controller;

import com.prosilion.scdecisionmatrix.security.service.AuthUserDetailsService;
import com.prosilion.scdecisionmatrix.service.AppUserAuthUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

class LoginController {
  private static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
  private final AuthUserDetailsService authUserDetailsService;
  private final AppUserAuthUserService appUserAuthUserService;

  @Autowired
  public LoginController(AuthUserDetailsService authUserDetailsService, AppUserAuthUserService appUserAuthUserService) {
    this.authUserDetailsService = authUserDetailsService;
    this.appUserAuthUserService = appUserAuthUserService;
  }
}
