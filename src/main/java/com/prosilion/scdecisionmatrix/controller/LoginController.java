package com.prosilion.scdecisionmatrix.controller;

import com.prosilion.scdecisionmatrix.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class LoginController {
  private static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

  private final UserDetailsService userDetailsService;

  @Autowired
  public LoginController(UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  @GetMapping("/login")
  public String showLogin(User user) {
    return "login";
  }

  @PostMapping("/loginuser")
  public String showLogin(@ModelAttribute User user, Model m) {
    UserDetails ud = userDetailsService.loadUserByUsername(user.getUsername());
    LOGGER.debug("logged in user retrieved from DB via UserDetails: {}", ud.getUsername());
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    LOGGER.debug("authentication principal: {}", auth.getPrincipal());
    m.addAttribute("name", ud.getUsername());
    m.addAttribute("role", ud.getPassword());
    return "welcome";
  }

  @RequestMapping({"/index", "/"})
  public String index() {
    return "userdetails";
  }
}
