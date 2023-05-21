package com.prosilion.scdecisionmatrix;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.prosilion.scdecisionmatrix.entity.Contract;
import com.prosilion.scdecisionmatrix.entity.User;
import com.prosilion.scdecisionmatrix.security.AuthUserDetailService;
import com.prosilion.scdecisionmatrix.service.ContractService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ContractServiceTest {
  @Autowired
  ContractService contractService;
  @Autowired
  AuthUserDetailService authUserDetailService;

  @Test
  void saveContractTest() {
    User payer = new User();
    payer.setUsername("contract payer");
//    Participant payee = new Participant();
//    payee.setName("payEE");

    Contract contract = new Contract();
    contract.setText("CONTRACT 111");
    //		contract.setPayee(payee);

    User user = authUserDetailService.save(payer);
    contract.setUser(user);
    Contract result = contractService.save(contract);
    assertEquals(contract.getText(), result.getText());
//    assertEquals("2222", result.getText());
    //		assertEquals(contract.getPayee(), result.getPayee());
  }
}
