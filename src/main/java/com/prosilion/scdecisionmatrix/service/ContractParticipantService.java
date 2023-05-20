package com.prosilion.scdecisionmatrix.service;

import com.prosilion.scdecisionmatrix.entity.Contract;
import com.prosilion.scdecisionmatrix.entity.Participant;

import java.util.List;
import java.util.Optional;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractParticipantService {
	private final ContractService contractService;
	private final ParticipantService participantService;

	@Autowired
	public ContractParticipantService(ContractService contractService, ParticipantService participantService) {
		this.contractService = contractService;
		this.participantService = participantService;
	}
	public Contract createContractFor(@NonNull Integer participantId) {
		return contractService.createContractFor(participantService.get(participantId).orElseThrow());
	}
	public Contract createContractFor(@NonNull Participant participant) {
		return contractService.createContractFor(participantService.get(participant).orElseThrow());
	}
	public List<Contract> showContracts() {
		return contractService.getAll();
	}
}
