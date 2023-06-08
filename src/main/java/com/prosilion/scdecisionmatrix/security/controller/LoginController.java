package com.prosilion.scdecisionmatrix.security.controller;

import com.prosilion.scdecisionmatrix.security.service.AuthUserDetailsService;
import com.prosilion.scdecisionmatrix.service.AppUserAuthUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

//@Controller
//@RequestMapping("/login")
class LoginController {
  private static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
  private final AuthUserDetailsService authUserDetailsService;
  private final AppUserAuthUserService appUserAuthUserService;

  @Autowired
  public LoginController(AuthUserDetailsService authUserDetailsService, AppUserAuthUserService appUserAuthUserService) {
    LOGGER.info("LoginCtonroller ctor() 00000000000000000000 ");
    LOGGER.info("LoginCtonroller ctor() 00000000000000000000 ");
    LOGGER.info("LoginCtonroller ctor() 00000000000000000000 ");
    this.authUserDetailsService = authUserDetailsService;
    this.appUserAuthUserService = appUserAuthUserService;
  }

//  @GetMapping("/login")
//  public String showLogin() {
//    LOGGER.info("LoginCtonroller showLogin() 11111111111111111111 ");
//    LOGGER.info("LoginCtonroller showLogin() 11111111111111111111 ");
//    return "login/login";
//  }

//  @PostMapping("/loginuser")
//  public String loginUser(@AuthenticationPrincipal AuthUserDetails user, Model m) {
//    LOGGER.info("LoginCtonroller loginUser() 22222222222222222222 ");
//    LOGGER.info("LoginCtonroller loginUser() 22222222222222222222 ");
//    AuthUserDetails authUserDetails = authUserDetailsService.loadUserByUsername(user.getUsername());
//    m.addAttribute("username", authUserDetails.getUsername());
//    return "login/welcome";
//  }
}
