package com.prosilion.scdecisionmatrix.controller;

import com.prosilion.scdecisionmatrix.security.entity.AuthUserDetails;
import com.prosilion.scdecisionmatrix.security.service.AuthUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
class LoginController {
  private static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

  private final AuthUserDetailsService authUserDetailsService;

  @Autowired
  public LoginController(AuthUserDetailsService authUserDetailsService) {
    this.authUserDetailsService = authUserDetailsService;
  }

  @GetMapping("/login")
  public String showLogin() {
    return "login/login";
  }

  @PostMapping("/loginuser")
  public String showLogin(@AuthenticationPrincipal AuthUserDetails user, Model m) {
    AuthUserDetails authUserDetails = authUserDetailsService.loadUserByUsername(user.getUsername());
    m.addAttribute("username", authUserDetails.getUsername());
    return "login/welcome";
  }
}
