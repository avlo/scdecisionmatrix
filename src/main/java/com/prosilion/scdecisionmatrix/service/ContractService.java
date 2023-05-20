package com.prosilion.scdecisionmatrix.service;

import com.prosilion.scdecisionmatrix.entity.Contract;
import com.prosilion.scdecisionmatrix.entity.Participant;
import com.prosilion.scdecisionmatrix.repository.ContractRepository;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService {
  private final ContractRepository contractRepository;
  private final ParticipantService participantService;

  @Autowired
  public ContractService(ContractRepository contractRepository, ParticipantService participantService) {
    this.contractRepository = contractRepository;
    this.participantService = participantService;
  }
  @Transactional
  public Contract createContractFor(@NonNull Participant participant) {
    Contract contract = new Contract();
    contract.setParticipant(participant);
    return this.save(contract);
  }
  @Transactional
  public Contract save(@NonNull Contract contract) {
    return contractRepository.save(contract);
  }
  public List<Contract> findContractsByParticipantId(@NonNull Integer userId) {
    return contractRepository.findContractsByParticipantId(userId);
  }
  public List<Contract> getAll() {
    return contractRepository.findAll();
  }
}
