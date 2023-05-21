package com.prosilion.scdecisionmatrix.service;

import com.prosilion.scdecisionmatrix.entity.Contract;
import com.prosilion.scdecisionmatrix.entity.User;
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
  public Contract createContractFor(@NonNull User user) {
    Contract contract = new Contract();
    contract.setUser(user);
    return this.save(contract);
  }

  @Transactional
  public Contract save(@NonNull Contract contract) {
    return contractRepository.save(contract);
  }

  public List<Contract> findContractsByParticipantId(@NonNull Long userId) {
    return contractRepository.findContractsByParticipantId(userId);
  }

  public List<Contract> getAll() {
    return contractRepository.findAll();
  }
}
