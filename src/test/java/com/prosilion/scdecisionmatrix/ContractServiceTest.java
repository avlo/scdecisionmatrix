package com.prosilion.scdecisionmatrix;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.prosilion.scdecisionmatrix.entity.Contract;
import com.prosilion.scdecisionmatrix.entity.Participant;
import com.prosilion.scdecisionmatrix.service.ContractService;
import com.prosilion.scdecisionmatrix.service.ParticipantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ContractServiceTest {
  @Autowired ContractService contractService;
  @Autowired ParticipantService participantService;

  @Test
  void saveContractTest() {
    Participant payer = new Participant();
    payer.setName("contract payer");
//    Participant payee = new Participant();
//    payee.setName("payEE");

    Contract contract = new Contract();
    contract.setText("CONTRACT 111");
    //		contract.setPayee(payee);

    Participant participant = participantService.save(payer);
    contract.setParticipant(participant);
    Contract result = contractService.save(contract);
    assertEquals(contract.getText(), result.getText());
//    assertEquals("2222", result.getText());
    //		assertEquals(contract.getPayee(), result.getPayee());
  }
}
