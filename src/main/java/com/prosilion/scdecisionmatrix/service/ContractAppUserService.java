package com.prosilion.scdecisionmatrix.service;

import com.prosilion.scdecisionmatrix.model.entity.Contract;
import com.prosilion.scdecisionmatrix.model.entity.ContractStateEnum;
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
  private final AppUserAuthUserService appUserAuthUserService;

  @Autowired
  public ContractAppUserService(ContractService contractService, AppUserAuthUserService appUserAuthUserService) {
    this.contractService = contractService;
    this.appUserAuthUserService = appUserAuthUserService;
  }

  @Transactional
  public Contract save(@NonNull Contract contract, @NonNull AuthUserDetails authUserDetails) {
    LOGGER.info("Creating contract [{}], authUserDetails name [{}]", contract.getText(), authUserDetails.getUsername());
    LOGGER.info("Retrieving authUser [{}]", getAppUserId(authUserDetails));
    // TODO: check below contract doesn't already have existing different appuser ID
    contract.setAppUserId(getAppUserId(authUserDetails));
    LOGGER.info("Set appUser [{}] to contract [{}]", getAppUserId(authUserDetails), contract.getId());
    return save(contract);
  }

  public List<Contract> getAllContracts() {
    return contractService.getAll();
  }
  public List<Contract> getAllContractsFor(@NonNull AuthUserDetails authUserDetails) {
    List<Contract> contracts = contractService.getContractsByAppUserId(getAppUserId(authUserDetails));
    contracts.addAll(contractService.getContractsByCoPartyId(getAppUserId(authUserDetails)));
    return contracts;
  }

  public List<Contract> getOpenContractsFor(@NonNull AuthUserDetails authUserDetails) {
    return contractService.getAvailableOppositeRoleContractsByAppUserId(getAppUserId(authUserDetails));
  }

  public Contract constructContract(AuthUserDetails authUserDetails) {
    return constructContract(getAppUserId(authUserDetails));
  }

  ////////////////////////
  //  PRIVATE METHODS
  ////////////////////////

  private Contract save(@NonNull Contract contract) {
    LOGGER.info("Saving contract [{}], appUser ID [{}], role [{}]", contract.getText(), contract.getAppUserId(), contract.getCreatorRole());
    Contract savedContract = contractService.save(contract);
    LOGGER.info("Contract saved [{}], appUser ID [{}], role [{}]", savedContract.getText(), savedContract.getAppUserId(), savedContract.getCreatorRole());
    return savedContract;
  }

  private Integer getAppUserId(AuthUserDetails authUserDetails) {
    return appUserAuthUserService.getAppUserId(authUserDetails);
  }

  private Contract constructContract(@NonNull Integer id) {
    Contract contract = new Contract();
    contract.setAppUserId(id);
    contract.setPayerState(ContractStateEnum.APPROVE);
    contract.setPayeeState(ContractStateEnum.APPROVE);
    return contract;
  }
}
