package com.prosilion.scdecisionmatrix.controller;

import com.prosilion.scdecisionmatrix.model.entity.Contract;
import com.prosilion.scdecisionmatrix.model.entity.security.AuthUserDetails;
import com.prosilion.scdecisionmatrix.service.ContractAppUserService;
import com.prosilion.scdecisionmatrix.service.ContractService;
import java.util.List;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contract")
public class ContractController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ContractController.class);
	private final ContractAppUserService joinTable;
	private final ContractService contractService;

	public ContractController(ContractAppUserService joinTable, ContractService contractService) {
		this.joinTable = joinTable;
		this.contractService = contractService;
	}

	@GetMapping("/display")
	public String showContracts(@AuthenticationPrincipal AuthUserDetails user, Model model) {
		Contract contract = contractService.constructContract(user);
		model.addAttribute("contracts", joinTable.getContracts(user));
		model.addAttribute("username", user.getUsername());
		model.addAttribute("contract", contract);
		LOGGER.info("Contract intermediate: {}", contract.toString());
		LOGGER.info("User for contract: {}", user.getUsername());
		return "contract/display";
	}

	@PostMapping("/create")
	public String createContract(@AuthenticationPrincipal AuthUserDetails user, @NonNull Contract contract, Model model) {
		LOGGER.info("Creating join table contract [{}], user [{}]", contract.getText(), user.getUsername());
		Contract savedContract = joinTable.save(contract, user);
		LOGGER.info("Contract join table entry created [{}], user [{}]", savedContract.getText(), user.getUsername());
		List<Contract> contractList = joinTable.getContracts(user);
		LOGGER.info("Contract join table list [{}], user [{}]", contractList.stream().toList(), user.getUsername());
		model.addAttribute("contracts", contractList);

		// below not really necessary ????
		Contract newContract = contractService.constructContract(user);
		LOGGER.info("Prepare to save new contract: [{}]", newContract.toString());
		LOGGER.info("User assigned to contract: [{}]", user.getUsername());
		model.addAttribute("contract", newContract);
		model.addAttribute("username", user.getUsername());
		return "contract/display";
	}
}