package com.prosilion.scdecisionmatrix.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class ContractDefaultLoginHandler implements AuthenticationSuccessHandler {
  private final static String SUCCESS_URL = "/contract/display_all";
  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
    new DefaultRedirectStrategy().sendRedirect(request, response, SUCCESS_URL);
  }
}
