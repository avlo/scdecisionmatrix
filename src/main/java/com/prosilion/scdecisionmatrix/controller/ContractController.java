package com.prosilion.scdecisionmatrix.controller;

import com.prosilion.scdecisionmatrix.entity.Contract;
import com.prosilion.scdecisionmatrix.security.entity.AuthUserDetails;
import com.prosilion.scdecisionmatrix.service.ContractContractUserService;
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
	private final ContractContractUserService contractContractUserService_JoinTable;

	public ContractController(ContractContractUserService contractContractUserService_JoinTable) {
		this.contractContractUserService_JoinTable = contractContractUserService_JoinTable;
	}

	@GetMapping("/")
	public String index() {
		return "redirect:/contract/display";
	}

	@GetMapping("/display")
	public String showContracts(@AuthenticationPrincipal AuthUserDetails user, Model model) {
		model.addAttribute("contracts", contractContractUserService_JoinTable.getContracts(user.getContractUser()));
		model.addAttribute("username", user.getUsername());
		Contract contract = new Contract();
		contract.setContractUser(user.getContractUser());
		model.addAttribute("contract", contract);
		return "contract/display";
	}

	@PostMapping("/create")
	public String createContract(@AuthenticationPrincipal AuthUserDetails user, @NonNull Contract contract, Model model) {
		Contract savedContract = contractContractUserService_JoinTable.save(contract, user);
		List<Contract> contracts = contractContractUserService_JoinTable.getContracts(user.getContractUser());
		contracts.add(savedContract);
		Contract newContract = new Contract();
		newContract.setContractUser(user.getContractUser());
		model.addAttribute("contract", newContract);
		model.addAttribute("contracts", contracts);
		model.addAttribute("username", user.getUsername());
		return "contract/display";
	}
}