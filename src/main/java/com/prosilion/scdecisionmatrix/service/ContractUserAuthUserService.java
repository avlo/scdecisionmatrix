package com.prosilion.scdecisionmatrix.service;

import com.prosilion.scdecisionmatrix.entity.ContractUser;
import com.prosilion.scdecisionmatrix.entity.ContractuserAuthuser;
import com.prosilion.scdecisionmatrix.entity.UserDto;
import com.prosilion.scdecisionmatrix.repository.ContractuserAuthuserRepository;
import com.prosilion.scdecisionmatrix.security.entity.AuthUserDetails;
import com.prosilion.scdecisionmatrix.security.service.AuthUserDetailsService;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

//@Service // this is a bean
public class ContractUserAuthUserService {
	private static Logger LOGGER = LoggerFactory.getLogger(ContractUserAuthUserService.class);
	private final ContractuserAuthuserRepository contractuserAuthuserRepository;
	private final AuthUserDetailsService authUserDetailsService;
	private final ContractUserService contractUserService;

	public ContractUserAuthUserService(AuthUserDetailsService authUserDetailsService,
			ContractUserService contractUserService, ContractuserAuthuserRepository contractuserAuthuserRepository) {
		this.authUserDetailsService = authUserDetailsService;
		this.contractUserService = contractUserService;
		this.contractuserAuthuserRepository = contractuserAuthuserRepository;
	}

	@Transactional
	public ContractuserAuthuser createUser(@NonNull UserDto userDto) {
		AuthUserDetails savedAuthUserDetails = authUserDetailsService.loadUserByUserDto(userDto);
		ContractUser contractUser = contractUserService.save(new ContractUser());
		ContractuserAuthuser contractuserAuthuser = new ContractuserAuthuser(contractUser.getId(), savedAuthUserDetails.getUsername());
		return contractuserAuthuserRepository.saveAndFlush(contractuserAuthuser);
	}

	public ContractuserAuthuser getContractuserAuthuser(@NonNull ContractUser contractUser) {
		return contractuserAuthuserRepository.findByContractuserId(contractUser.getId()).get();
	}

	public ContractuserAuthuser getContractuserAuthuser(@NonNull AuthUserDetails authUserDetails) {
		return contractuserAuthuserRepository.findByAuthuserName(authUserDetails.getUsername()).get();
	}
}
