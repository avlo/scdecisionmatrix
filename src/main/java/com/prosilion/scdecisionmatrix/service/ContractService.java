package com.prosilion.scdecisionmatrix.service;

import com.prosilion.scdecisionmatrix.model.entity.AppUser;
import com.prosilion.scdecisionmatrix.model.entity.Contract;
import com.prosilion.scdecisionmatrix.repository.ContractRepository;
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
    //  TODD: Precondition contract appuser not null prior to save
    return contractRepository.save(contract);
  }

  public Contract getContractById(@NonNull Integer id) {
    return contractRepository.getContractById(id).get();
  }

  public List<Contract> getContractsByAppUser(@NonNull AppUser appUser) {
    return getContractsByAppUserId(appUser.getId());
  }

  public List<Contract> getAvailableOppositeRoleContractsByAppUser(@NonNull AppUser appUser) {
    return getAvailableOppositeRoleContractsByAppUserId(appUser.getId());
  }

  public List<Contract> getAvailableOppositeRoleContractsByAppUserId(@NonNull Integer id) {
    return contractRepository.getAvailableOppositeRoleContracts(id);
  }

  public List<Contract> getContractsByAppUserId(@NonNull Integer id) {
    return contractRepository.getContractsByAppUserId(id);
  }

  public List<Contract> getAll() {
    return contractRepository.findAll();
  }
}
