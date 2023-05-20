package com.prosilion.scdecisionmatrix.controller;

import com.prosilion.scdecisionmatrix.entity.Participant;
import com.prosilion.scdecisionmatrix.service.ContractParticipantService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.ModelAndView;

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
//  public ModelAndView displayContracts(@PathVariable @NonNull Integer payerId) {  // TODO: display for payerId
  public String formGet(Model model) {
    model.addAttribute("contracts", contractParticipantService.showContracts());
    return "contract/form";
  }

  @PostMapping("/form")
  //  public ModelAndView createContract(@PathVariable @NonNull Integer payerId) { //TODO: create for payerId
  public String createContract(Participant participant, Model model) {
//    model.addAttribute("participant", participant);
    model.addAttribute("contract", contractParticipantService.createContractFor(participant));
    return "contract/form";
  }
}
