package com.prosilion.scdecisionmatrix.service;

import com.prosilion.scdecisionmatrix.model.entity.Contract;
import com.prosilion.scdecisionmatrix.model.entity.AppUser;
import com.prosilion.scdecisionmatrix.model.entity.AppUserAuthUser;
import com.prosilion.scdecisionmatrix.model.entity.security.AuthUserDetails;
import java.util.List;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContractAppUserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ContractAppUserService.class);
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
		LOGGER.info("Saving contract [{}], appUser ID [{}]", contract.getText(), appUser.getId());
		contract.setAppUser(appUser);
		LOGGER.info("Contract saved [{}], appUser ID [{}]", contract.getText(), appUser.getId());
		return contractService.save(contract);
	}

	public Contract save(@NonNull Contract contract, @NonNull AuthUserDetails authUserDetails) {
		LOGGER.info("Saving contract [{}], appUser ID [{}]", contract.getText(), authUserDetails.getUsername());
		return save(contract, getAppUser(authUserDetails));
	}

	public List<Contract> getContracts(@NonNull AppUser user) {
		return contractService.getContracts(user);
	}

	public List<Contract> getContracts(@NonNull AuthUserDetails authUserDetails) {
		return contractService.getContracts(getAppUser(authUserDetails));
	}

	private AppUser getAppUser(@NonNull AuthUserDetails authUserDetails) {
		AppUserAuthUser user = appUserAuthUserService.getAppUserAuthUser(authUserDetails);
		LOGGER.info("Attempt to get user [{}]", user.getAuthUserName());
		AppUser appUser = appUserService.findById(user.getId());
		LOGGER.info("Retreived appUser [{}]", appUser.getId());
		return appUser;
	}
}