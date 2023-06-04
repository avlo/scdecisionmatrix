package com.prosilion.scdecisionmatrix.service;

import com.prosilion.scdecisionmatrix.entity.ContractUser;
import com.prosilion.scdecisionmatrix.repository.ContractUserRepository;
import com.prosilion.scdecisionmatrix.security.entity.AuthUserDetails;
import com.prosilion.scdecisionmatrix.security.service.AuthUserDetailsService;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContractUserService {
	private final ContractUserRepository contractUserRepository;

	public ContractUserService(ContractUserRepository contractUserRepository) {
		this.contractUserRepository = contractUserRepository;
	}

	public ContractUser findById(Integer id) {
		return contractUserRepository.findById(id).orElse(new ContractUser());
	}

	public ContractUser findByContractUser(@NonNull ContractUser contractUser) {
		return findById(contractUser.getId());
	}

	@Transactional
	public ContractUser save(@NonNull ContractUser contractUser) {
		ContractUser contractUserToSave = findByContractUser(contractUser);
		return contractUserToSave.getId() != null ? contractUserToSave : contractUserRepository.save(contractUserToSave);
	}

//	@Transactional
//	public ContractUser createUser(@NonNull AuthUserDetails authUserDetails) {
//		return contractUserRepository.save(authUserDetails.authgetContractUser())
//
//	}
}
