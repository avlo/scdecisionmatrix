package com.prosilion.scdecisionmatrix.service;

import com.prosilion.scdecisionmatrix.entity.Contract;
import com.prosilion.scdecisionmatrix.entity.ContractUser;
import com.prosilion.scdecisionmatrix.entity.ContractuserAuthuser;
import com.prosilion.scdecisionmatrix.security.entity.AuthUserDetails;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContractContractUserService {
	private final ContractService contractService;
	private final ContractUserService contractUserService;
	private final ContractUserAuthUserService contractUserAuthUserService;

	@Autowired
	public ContractContractUserService(
			ContractService contractService, ContractUserService contractUserService, ContractUserAuthUserService contractUserAuthUserService) {
		this.contractService = contractService;
		this.contractUserService = contractUserService;
		this.contractUserAuthUserService = contractUserAuthUserService;
	}

	@Transactional
	public Contract save(@NonNull Contract contract, @NonNull ContractUser contractUser) {
		contract.setContractUser(contractUserService.save(contractUser));
		return contractService.save(contract);
	}

	public Contract save(@NonNull Contract contract, @NonNull AuthUserDetails authUserDetails) {
		return save(contract, getContractUser(authUserDetails));
	}

	public List<Contract> getContracts(@NonNull ContractUser user) {
		return contractService.getContracts(user);
	}

	public List<Contract> getContracts(@NonNull AuthUserDetails authUserDetails) {
		return contractService.getContracts(getContractUser(authUserDetails));
	}

	private ContractUser getContractUser(@NonNull AuthUserDetails authUserDetails) {
		ContractuserAuthuser user = contractUserAuthUserService.getContractuserAuthuser(authUserDetails);
		return contractUserService.findById(user.getId());
	}
}