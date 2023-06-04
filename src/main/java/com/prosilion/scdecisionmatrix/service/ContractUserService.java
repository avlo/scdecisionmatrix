package com.prosilion.scdecisionmatrix.service;

import com.prosilion.scdecisionmatrix.entity.ContractUser;
import com.prosilion.scdecisionmatrix.repository.ContractUserRepository;
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
		return contractUserRepository.findById(id).get();
	}

	public ContractUser findByContractUser(@NonNull ContractUser contractUser) {
		if (contractUser.getId() != null) {
			return findById(contractUser.getId());
		}
		return new ContractUser();
	}

	@Transactional
	public ContractUser save(@NonNull ContractUser contractUser) {
		ContractUser contractUserToSave = findByContractUser(contractUser);
		return contractUserToSave.getId() != null ? contractUserToSave : contractUserRepository.save(contractUserToSave);
	}

//	@Transactional
//	public ContractUser createUser(@NonNull ContractUser contractUser) {
//		return contractUserRepository.save(authUserDetails.authgetContractUser());
//	}
}
