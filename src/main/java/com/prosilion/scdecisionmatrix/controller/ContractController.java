package com.prosilion.scdecisionmatrix.controller;

import com.prosilion.scdecisionmatrix.entity.Contract;
import com.prosilion.scdecisionmatrix.security.entity.AuthUserDetails;
import com.prosilion.scdecisionmatrix.service.ContractContractUserService;
import com.prosilion.scdecisionmatrix.service.ContractService;
import java.util.List;
import lombok.NonNull;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contract")
public class ContractController {
	private final ContractContractUserService joinTable;
	private final ContractService contractService;

	public ContractController(ContractContractUserService joinTable, ContractService contractService) {
		this.joinTable = joinTable;
		this.contractService = contractService;
	}

	@GetMapping("/display")
	public String showContracts(@AuthenticationPrincipal AuthUserDetails user, Model model) {
		Contract contract = contractService.constructContract(user);
		model.addAttribute("contracts", joinTable.getContracts(user));
		model.addAttribute("username", user.getUsername());
		model.addAttribute("contract", contract);
		return "contract/display";
	}

	@PostMapping("/create")
	public String createContract(@AuthenticationPrincipal AuthUserDetails user, @NonNull Contract contract, Model model) {
		joinTable.save(contract, user);
		model.addAttribute("contracts", joinTable.getContracts(user));

//		above line should be showing all contract, but doesn't.  needs testing, but below works
//		contracts.add(savedContract);

		// below not really necessary
		Contract newContract = contractService.constructContract(user);
		model.addAttribute("contract", newContract);
		model.addAttribute("username", user.getUsername());
		return "contract/display";
	}
}