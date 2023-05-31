package com.prosilion.scdecisionmatrix.controller;

import com.prosilion.scdecisionmatrix.security.AuthUserDetails;
import com.prosilion.scdecisionmatrix.security.AuthUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class LoginController {
  private static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

  private final AuthUserDetailsService authUserDetailsService;

  @Autowired
  public LoginController(AuthUserDetailsService authUserDetailsService) {
    this.authUserDetailsService = authUserDetailsService;
  }

  @GetMapping("/login")
  public String showLogin(AuthUserDetails user) {
    return "login";
  }

  @PostMapping("/loginuser")
  public String showLogin(@ModelAttribute AuthUserDetails authUserDetailsParameter, Authentication authenticationParameter, Model m) {
    AuthUserDetails authUserDetails = authUserDetailsService.loadUserByUsername(authUserDetailsParameter.getUsername());
    debug(authUserDetailsParameter, authenticationParameter);
    m.addAttribute("username", authUserDetails.getUsername());
    return "welcome";
  }

  private void debug(AuthUserDetails authUserDetailsParameter, Authentication authenticationParameter) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    AuthUserDetails authUserDetails = authUserDetailsService.loadUserByUsername(authUserDetailsParameter.getUsername());
    LOGGER.info("Autowired AuthUserDetils parameter: {}", authUserDetailsParameter);
    LOGGER.info("Autowired Authentication parameter: {}", authenticationParameter);
    LOGGER.info("AuthUserDetails from Service: {}", authUserDetails);
    LOGGER.info("Static Security context Authentication object: {}", authentication);
  }
}
