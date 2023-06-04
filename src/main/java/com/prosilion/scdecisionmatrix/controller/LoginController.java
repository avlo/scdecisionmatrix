package com.prosilion.scdecisionmatrix.controller;

import com.prosilion.scdecisionmatrix.entity.ContractuserAuthuser;
import com.prosilion.scdecisionmatrix.security.entity.AuthUserDetails;
import com.prosilion.scdecisionmatrix.security.service.AuthUserDetailsService;
import com.prosilion.scdecisionmatrix.service.ContractUserAuthUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/login")
class LoginController {
  private static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
  private final AuthUserDetailsService authUserDetailsService;
  private final ContractUserAuthUserService contractUserAuthUserService;

  @Autowired
  public LoginController(AuthUserDetailsService authUserDetailsService, ContractUserAuthUserService contractUserAuthUserService) {
    LOGGER.info("LoginCtonroller ctor() 00000000000000000000 ");
    LOGGER.info("LoginCtonroller ctor() 00000000000000000000 ");
    LOGGER.info("LoginCtonroller ctor() 00000000000000000000 ");
    this.authUserDetailsService = authUserDetailsService;
    this.contractUserAuthUserService = contractUserAuthUserService;
  }

  @GetMapping("/login")
  public String showLogin() {
    LOGGER.info("LoginCtonroller showLogin() 11111111111111111111 ");
    LOGGER.info("LoginCtonroller showLogin() 11111111111111111111 ");
    return "login/login";
  }

  @PostMapping("/loginuser")
  public String loginUser(@AuthenticationPrincipal AuthUserDetails user, Model m) {
    LOGGER.info("LoginCtonroller loginUser() 22222222222222222222 ");
    LOGGER.info("LoginCtonroller loginUser() 22222222222222222222 ");
    AuthUserDetails authUserDetails = authUserDetailsService.loadUserByUsername(user.getUsername());
    m.addAttribute("username", authUserDetails.getUsername());
    return "login/welcome";
  }

  @PostMapping("/createuser")
  public String registerUser(@AuthenticationPrincipal AuthUserDetails auth, Model m) {
    LOGGER.info("LoginCtonroller registerUser() 33333333333333333333 ");
    LOGGER.info("LoginCtonroller registerUser() 33333333333333333333 ");
    ContractuserAuthuser contractuserAuthuser = contractUserAuthUserService.createUser(auth);
    m.addAttribute("username", contractuserAuthuser.getAuthuserName());
    return "login/welcome";
  }
}
