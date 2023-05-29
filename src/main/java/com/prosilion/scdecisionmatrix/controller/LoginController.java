package com.prosilion.scdecisionmatrix.controller;

import com.prosilion.scdecisionmatrix.entity.User;
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

  @Autowired UserDetailsService userDetailsService;

  @GetMapping("/login")
  public String showLogin(User user) {
    return "login.html";
  }

  @GetMapping(value = "/home")
  public String printWelcome(User user) {
    return "login.html";
  }

  @PostMapping("/loginuser")
  public String showLogin(@ModelAttribute User user, Model m) {
    System.out.println("LoginController 0000000000");
    System.out.println("LoginController 0000000000");
    UserDetails ud = userDetailsService.loadUserByUsername(user.getUsername());
    System.out.println("User: " + ud.getUsername());
    System.out.println("Authorities" + user.getUsername());
    m.addAttribute("name", user.getUsername());
    m.addAttribute("role", user.getPassword());
    System.out.println("LoginController 0000000000");
    System.out.println("LoginController 0000000000");
    return "welcome.html";
  }

  @RequestMapping({"/index", "/"})
  public String index() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return "userdetails.html";
  }
}
