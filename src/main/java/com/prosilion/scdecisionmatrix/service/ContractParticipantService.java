package com.prosilion.scdecisionmatrix.service;

import com.prosilion.scdecisionmatrix.entity.Contract;
import com.prosilion.scdecisionmatrix.entity.Participant;

import java.util.List;

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
	public Contract saveContractFor(Contract contract, @NonNull Integer participantId) {
    System.out.println("CCCCCCCCCC");
		System.out.println("CCCCCCCCCC");
    System.out.println(contract.getText());
		System.out.println("CCCCCCCCCC");
		System.out.println("CCCCCCCCCC");
		Participant participant = participantService.get(participantId).orElseThrow();
		contract.setParticipant(participant);
		Contract newContract = contractService.save(contract);
		return newContract;
	}
	public Contract createContractFor(@NonNull Participant participant) {
		return contractService.createContractFor(participantService.get(participant).orElseThrow());
	}
	public List<Contract> getContracts() {
		return contractService.getAll();
	}
	public List<Contract> getContractsFor(@NonNull Integer userId) {
		return contractService.findContractsByParticipantId(userId);
	}
}
