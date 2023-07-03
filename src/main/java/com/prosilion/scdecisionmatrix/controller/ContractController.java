package com.prosilion.scdecisionmatrix.controller;

import com.prosilion.scdecisionmatrix.model.entity.Contract;
import com.prosilion.scdecisionmatrix.model.entity.ContractStateEnum;
import com.prosilion.scdecisionmatrix.model.entity.CreatorRoleEnum;
import com.prosilion.scdecisionmatrix.model.entity.security.AuthUserDetails;
import com.prosilion.scdecisionmatrix.service.AppUserAuthUserService;
import com.prosilion.scdecisionmatrix.service.ContractAppUserService;
import com.prosilion.scdecisionmatrix.service.ContractService;
import java.util.List;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contract")
public class ContractController {
  private static final Logger LOGGER = LoggerFactory.getLogger(ContractController.class);
  private final ContractAppUserService joinTable;
  private final ContractService contractService;
  private final AppUserAuthUserService appUserAuthUserService;

  @Autowired
  public ContractController(ContractAppUserService joinTable, ContractService contractService, AppUserAuthUserService appUserAuthUserService) {
    this.joinTable = joinTable;
    this.contractService = contractService;
    this.appUserAuthUserService = appUserAuthUserService;
  }

  @PostMapping("/create")
  public String createContract(@AuthenticationPrincipal AuthUserDetails user, @NonNull Contract contract, Model model) {
    joinTable.save(contract, user);
    setCanonicalModelAttributes(user, model);
    return "contract/display";
  }

  @GetMapping("/display_user_contracts")
  public String showUserContracts(@AuthenticationPrincipal AuthUserDetails user, Model model) {
    setCanonicalModelAttributes(user, model);
    return "contract/display";
  }

  @GetMapping("/display_contract/{id}")
  public String showAvailableContracts(@AuthenticationPrincipal AuthUserDetails user, @PathVariable("id") Integer id, Model model) {
    LOGGER.info("Fetching selected contract: [{}]", id);
    Contract contract = contractService.getContractById(id);
    model.addAttribute("contract", contract);
    model.addAttribute("username", user.getUsername());
    model.addAttribute("counterPartyId", appUserAuthUserService.getAppUserId(user));
    LOGGER.info("CounterPartyId: [{}]", appUserAuthUserService.getAppUserId(user));
    LOGGER.info("User for potential contract: {}", user.getUsername());
    return "contract/preview_contract";
  }

  @GetMapping("/my_contract/{id}")
  public String showMyContracts(@AuthenticationPrincipal AuthUserDetails user, @PathVariable("id") Integer id, Model model) {
    LOGGER.info("Fetching my contract: [{}]", id);
    Contract contract = contractService.getContractById(id);
    model.addAttribute("contract", contract);
    model.addAttribute("username", user.getUsername());
    model.addAttribute("role", new RoleDeterminer(contract, user).getRoleEnum(contract.getCreatorRole()));
    return "contract/view_contract";
  }

  @PostMapping("/apply")
  public String applyForContract(@AuthenticationPrincipal AuthUserDetails user, Contract contract, Model model) {
    contractService.save(contract);
    List<Contract> contractList = joinTable.getAllContracts();
    model.addAttribute("contracts", contractList);
    return "redirect:display_user_contracts";
  }

  @PostMapping("/vote")
  public String voteOnContract(@AuthenticationPrincipal AuthUserDetails user, Contract contract, Model model) {
    LOGGER.info("User [{}] voting on contract [{}]", user.getUsername(), contract);
    LOGGER.info("Contract id: [{}] ", contract.getId());
    LOGGER.info("Contract text: [{}] ", contract.getText());
    LOGGER.info("Contract appUserId: [{}] ", contract.getAppUserId());
    contractService.save(contract);
    List<Contract> contractList = joinTable.getAllContracts();
    model.addAttribute("contracts", contractList);
    return "redirect:display_user_contracts";
  }
  /////////////////////////
  // private methods
  /////////////////////////

  private void setCanonicalModelAttributes(@NonNull AuthUserDetails user, @NonNull Model model) {
    model.addAttribute("user_contracts", joinTable.getAllContractsFor(user));
    model.addAttribute("open_contracts", joinTable.getOpenContractsFor(user));
    model.addAttribute("contract", joinTable.constructContract(user));
    model.addAttribute("username", user.getUsername());
  }

  private class RoleDeterminer {
    Integer contractAppUserId;
    Integer appUserId;

    public RoleDeterminer(@NonNull Contract contract, @NonNull AuthUserDetails user) {
      this.contractAppUserId = contract.getAppUserId();
      this.appUserId = appUserAuthUserService.getAppUserId(user);
    }

    CreatorRoleEnum getRoleEnum(CreatorRoleEnum role) {
      if (contractAppUserId.equals(appUserId) && role.equals(CreatorRoleEnum.PAYER)) {
        return CreatorRoleEnum.PAYER;
      }
      if (contractAppUserId.equals(appUserId) && role.equals(CreatorRoleEnum.PAYEE)) {
        return CreatorRoleEnum.PAYEE;
      }
      if (!contractAppUserId.equals(appUserId) && role.equals(CreatorRoleEnum.PAYER)) {
        return CreatorRoleEnum.PAYEE;
      }
      return CreatorRoleEnum.PAYER;
    }
  }
}
