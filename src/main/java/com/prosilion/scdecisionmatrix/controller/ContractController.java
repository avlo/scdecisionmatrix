package com.prosilion.scdecisionmatrix.controller;

import com.prosilion.scdecisionmatrix.entity.Contract;
import com.prosilion.scdecisionmatrix.entity.Participant;
import com.prosilion.scdecisionmatrix.service.ContractParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/contract")
public class ContractController {
  private final ContractParticipantService contractParticipantService;

  @Autowired
  public ContractController(ContractParticipantService contractParticipantService) {
    this.contractParticipantService = contractParticipantService;
  }
  @GetMapping("/")
  public String index() {
    return "redirect:/contract/form";
  }

  @GetMapping("/form")
  public String formGet(@RequestParam(value = "userId") Integer userId, Model model) {
    System.out.println("AAAAAAAAAAA");
    System.out.println("AAAAAAAAAAA");
    model.addAttribute("contracts", contractParticipantService.getContractsFor(userId));
    model.addAttribute("userId", userId);
    return "contract/form";
  }

  @PostMapping("/form")
  public String createContract(@RequestParam(value = "userId") Integer userId, Contract contract, Model model) {
    System.out.println("BBBBBBBBBBBB");
    System.out.println("BBBBBBBBBBBB");
    contractParticipantService.saveContractFor(contract, userId);
    model.addAttribute("contracts", contractParticipantService.getContractsFor(userId));
    model.addAttribute("userId", userId);
    System.out.println("BBBBBBBBBBBB");
    System.out.println("BBBBBBBBBBBB");
    return "contract/form";
  }
}
