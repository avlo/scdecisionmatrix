package com.prosilion.scdecisionmatrix.controller;

import com.prosilion.scdecisionmatrix.entity.Contract;
import com.prosilion.scdecisionmatrix.service.ContractUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/contract")
public class ContractController {
  private final ContractUserService contractUserService;

  public ContractController(ContractUserService contractUserService) {
    this.contractUserService = contractUserService;
  }

  @GetMapping("/")
  public String index() {
    return "redirect:/contract/form";
  }

  @GetMapping("/form")
  public String formGet(@RequestParam(value = "userId") Long userId, Model model) {
    System.out.println("AAAAAAAAAAA");
    System.out.println("AAAAAAAAAAA");
    model.addAttribute("contracts", contractUserService.getContractsFor(userId));
    model.addAttribute("userId", userId);
    return "contract/form";
  }

  @PostMapping("/form")
  public String createContract(
      @RequestParam(value = "userId") Long userId, Contract contract, Model model) {
    System.out.println("BBBBBBBBBBBB");
    System.out.println("BBBBBBBBBBBB");
    contractUserService.saveContractFor(contract, userId);
    model.addAttribute("contracts", contractUserService.getContractsFor(userId));
    model.addAttribute("userId", userId);
    System.out.println("BBBBBBBBBBBB");
    System.out.println("BBBBBBBBBBBB");
    return "contract/form";
  }
}
