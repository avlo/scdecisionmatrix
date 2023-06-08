package com.prosilion.scdecisionmatrix.security.controller;

import com.prosilion.scdecisionmatrix.security.entity.AuthUserDetails;
import com.prosilion.scdecisionmatrix.security.service.AuthUserDetailsService;
import com.prosilion.scdecisionmatrix.service.AppUserAuthUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
class LoginController {
  private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
  private final AuthUserDetailsService authUserDetailsService;

  @Autowired
  public LoginController(AuthUserDetailsService authUserDetailsService) {
    this.authUserDetailsService = authUserDetailsService;
  }

//  @PostMapping("/loginuser")
//  public String loginUser(@AuthenticationPrincipal AuthUserDetails user, Model m) {
//    AuthUserDetails authUserDetails = authUserDetailsService.loadUserByUsername(user.getUsername());
//    LOGGER.info("###################");
//    LOGGER.info("###################");
//    LOGGER.info("###################");
//    LOGGER.info("Retrieved AuthUserDetails User {}", authUserDetails.getUsername());
//    m.addAttribute("username", authUserDetails.getUsername());
//    return "/users";
//  }
}
