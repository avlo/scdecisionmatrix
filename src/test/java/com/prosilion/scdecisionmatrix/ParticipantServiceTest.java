package com.prosilion.scdecisionmatrix;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.prosilion.scdecisionmatrix.entity.Contract;
import com.prosilion.scdecisionmatrix.entity.Participant;
import com.prosilion.scdecisionmatrix.service.ParticipantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ParticipantServiceTest {
  @Autowired ParticipantService participantService;

  @Test
  void saveParticipantTest() {
    Participant payer = new Participant();
    payer.setName("participant payer");

//    Contract contract = new Contract();
//    contract.setText("1111111111");
//
//    payer.setContract(contract);

    Participant result = participantService.save(payer);
    assertEquals(result.getName(), payer.getName());
    //		assertEquals(contract.getPayee(), result.getPayee());
  }
}
