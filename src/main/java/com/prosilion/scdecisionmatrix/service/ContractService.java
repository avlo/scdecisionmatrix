package com.prosilion.scdecisionmatrix.service;

import com.prosilion.scdecisionmatrix.entity.Contract;
import com.prosilion.scdecisionmatrix.entity.ContractUser;
import com.prosilion.scdecisionmatrix.repository.ContractRepository;
import com.prosilion.scdecisionmatrix.security.entity.AuthUserDetails;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractService {
	private final ContractRepository contractRepository;

	@Autowired
	public ContractService(ContractRepository contractRepository) {
		this.contractRepository = contractRepository;
	}

	@Transactional
	public Contract save(@NonNull Contract contract) {
		return contractRepository.save(contract);
	}

	public List<Contract> getContracts(@NonNull ContractUser user) {
		return contractRepository.getContracts(user.getId());
	}

	public List<Contract> getAll() {
		return contractRepository.findAll();
	}

	public Contract constructContract(AuthUserDetails user) {
		return constructContract(user.getContractUser());
	}

	public Contract constructContract(ContractUser user) {
		Contract contract = new Contract();
		contract.setContractUser(user);
		return contract;
	}
}
