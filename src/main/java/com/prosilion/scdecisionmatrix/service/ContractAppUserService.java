package com.prosilion.scdecisionmatrix.service;

import com.prosilion.scdecisionmatrix.entity.AppUser;
import com.prosilion.scdecisionmatrix.entity.AppuserAuthuser;
import com.prosilion.scdecisionmatrix.entity.Contract;
import com.prosilion.scdecisionmatrix.security.entity.AuthUserDetails;
import java.util.List;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContractAppUserService {
	private final ContractService contractService;
	private final AppUserService appUserService;
	private final AppUserAuthUserService appUserAuthUserService;

	@Autowired
	public ContractAppUserService(
			ContractService contractService, AppUserService appUserService, AppUserAuthUserService appUserAuthUserService) {
		this.contractService = contractService;
		this.appUserService = appUserService;
		this.appUserAuthUserService = appUserAuthUserService;
	}

	@Transactional
	public Contract save(@NonNull Contract contract, @NonNull AppUser appUser) {
		contract.setAppUser(appUserService.save(appUser));
		return contractService.save(contract);
	}

	public Contract save(@NonNull Contract contract, @NonNull AuthUserDetails authUserDetails) {
		return save(contract, getAppUser(authUserDetails));
	}

	public List<Contract> getContracts(@NonNull AppUser user) {
		return contractService.getContracts(user);
	}

	public List<Contract> getContracts(@NonNull AuthUserDetails authUserDetails) {
		return contractService.getContracts(getAppUser(authUserDetails));
	}

	private AppUser getAppUser(@NonNull AuthUserDetails authUserDetails) {
		AppuserAuthuser user = appUserAuthUserService.getAppuserAuthuser(authUserDetails);
		return appUserService.findById(user.getId());
	}
}