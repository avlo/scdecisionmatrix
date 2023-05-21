package com.prosilion.scdecisionmatrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ScdecisionmatrixApplication {

  public static void main(String[] args) {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    System.out.println("0000000000000000000");
    System.out.println("0000000000000000000");
    System.out.println(encoder.encode("user"));
    System.out.println("0000000000000000000");
    System.out.println("0000000000000000000");
    SpringApplication.run(ScdecisionmatrixApplication.class, args);
  }
}
