package com.prosilion.scdecisionmatrix.controller;

import com.prosilion.scdecisionmatrix.entity.User;
import com.prosilion.scdecisionmatrix.security.AuthUserDetailService;
import com.prosilion.scdecisionmatrix.security.AuthUserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/user")
public class UserController {

  private final UserDetailsService authUserDetailsService;
  @Autowired
  public UserController(UserDetailsService authUserDetailsService) {
    this.authUserDetailsService = authUserDetailsService;
  }

  @GetMapping("/")
  public String index() {
    System.out.println("0000000000000");
    System.out.println("0000000000000");
    return "redirect:/user/form";
  }

  @GetMapping("/form")
  public String formGet() {
    System.out.println("11111111111111");
    System.out.println("11111111111111");
    return "/user/form";
  }

  @PostMapping("/form")
  public String createParticipant(User user, Model model) {
    System.out.println("UserController 333333333333333");
    System.out.println("UserController 333333333333333");
    UserDetails ud = authUserDetailsService.loadUserByUsername(user.getUsername());
    System.out.println("UserDetails: " + ud.getUsername());
    System.out.println("UserController 333333333333333");
    System.out.println("UserController 333333333333333");
    model.addAttribute("user", user);
    return "/user/form";
  }
}
