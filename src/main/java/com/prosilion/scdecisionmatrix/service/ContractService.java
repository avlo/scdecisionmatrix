package com.prosilion.scdecisionmatrix.service;

import com.prosilion.scdecisionmatrix.entity.AppUser;
import com.prosilion.scdecisionmatrix.entity.Contract;
import com.prosilion.scdecisionmatrix.entity.AppuserAuthuser;
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
	private final AppUserService appUserService;
	private final AppUserAuthUserService appUserAuthUserService;
	@Autowired
	public ContractService(ContractRepository contractRepository, AppUserAuthUserService appUserAuthUserService, AppUserService appUserService) {
		this.contractRepository = contractRepository;
		this.appUserAuthUserService = appUserAuthUserService;
		this.appUserService = appUserService;
	}

	@Transactional
	public Contract save(@NonNull Contract contract) {
		return contractRepository.save(contract);
	}

	public List<Contract> getContracts(@NonNull AppUser user) {
		return contractRepository.getContracts(user.getId());
	}

	public List<Contract> getAll() {
		return contractRepository.findAll();
	}

	public Contract constructContract(AuthUserDetails authUserDetails) {
		AppuserAuthuser appuserAuthuser = appUserAuthUserService.getAppuserAuthuser(authUserDetails);
		AppUser appUser = appUserService.findById(appuserAuthuser.getAppuserId());
		return constructContract(appUser);
	}

	public Contract constructContract(AppUser user) {
		Contract contract = new Contract();
		contract.setAppUser(user);
		return contract;
	}
}
