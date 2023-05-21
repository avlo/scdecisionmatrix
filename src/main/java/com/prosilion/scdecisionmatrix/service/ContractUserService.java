package com.prosilion.scdecisionmatrix.service;

import com.prosilion.scdecisionmatrix.entity.Contract;
import com.prosilion.scdecisionmatrix.entity.User;
import com.prosilion.scdecisionmatrix.security.AuthUserDetailService;
import java.util.List;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractUserService {
  private final ContractService contractService;
  private final AuthUserDetailService userDetailService;

  @Autowired
  public ContractUserService(
      ContractService contractService, AuthUserDetailService userDetailService) {
    this.contractService = contractService;
    this.userDetailService = userDetailService;
  }

  public Contract saveContractFor(Contract contract, @NonNull Long userId) {
    System.out.println("CCCCCCCCCC");
    System.out.println("CCCCCCCCCC");
    System.out.println(contract.getText());
    System.out.println("CCCCCCCCCC");
    System.out.println("CCCCCCCCCC");
    User user = userDetailService.loadUserById(userId);
    contract.setUser(user);
    Contract newContract = contractService.save(contract);
    return newContract;
  }

  public Contract createContractFor(@NonNull User user) {
    return contractService.createContractFor(userDetailService.loadUserById(user.getId()));
  }

  public List<Contract> getContracts() {
    return contractService.getAll();
  }

  public List<Contract> getContractsFor(@NonNull Long userId) {
    return contractService.findContractsByParticipantId(userId);
  }
}
