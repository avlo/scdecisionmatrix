package com.prosilion.scdecisionmatrix.service;

import com.prosilion.scdecisionmatrix.entity.ContractUser;
import com.prosilion.scdecisionmatrix.repository.ContractUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContractUserService {
	private final ContractUserRepository contractUserRepository;

	public ContractUserService(ContractUserRepository contractUserRepository) {
		this.contractUserRepository = contractUserRepository;
	}

	public ContractUser findById(Long id) {
		return contractUserRepository.findById(id).orElse(new ContractUser());
	}

	public ContractUser findByContractUser(ContractUser contractUser) {
		return findById(contractUser.getId());
	}

	@Transactional
	public ContractUser save(ContractUser contractUser) {
		return contractUserRepository.save(findByContractUser(contractUser));
	}
}
