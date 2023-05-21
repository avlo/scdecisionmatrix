package com.prosilion.scdecisionmatrix;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.prosilion.scdecisionmatrix.entity.User;
import com.prosilion.scdecisionmatrix.security.AuthUserDetailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ParticipantServiceTest {
  @Autowired AuthUserDetailService authUserDetailService;

  @Test
  void saveParticipantTest() {
    User payer = new User();
    payer.setUsername("participant payer");

    //    Contract contract = new Contract();
    //    contract.setText("1111111111");
    //
    //    payer.setContract(contract);

    User result = authUserDetailService.save(payer);
    assertEquals(result.getUsername(), payer.getUsername());
    //		assertEquals(contract.getPayee(), result.getPayee());
  }
}
