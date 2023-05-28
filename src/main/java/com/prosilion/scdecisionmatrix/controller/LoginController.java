package com.prosilion.scdecisionmatrix.controller;

import com.prosilion.scdecisionmatrix.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
class LoginController {
  @GetMapping("/login")
  public String showLogin(User user) {
    return "index";
  }

  @PostMapping("/loginuser")
  public String showLogin(@ModelAttribute User user, Model m) {
    String uname = user.getUsername();
    String pass = user.getPassword();
    m.addAttribute("uname", uname);
    m.addAttribute("pass", pass);
    return "welcome";
  }
}
