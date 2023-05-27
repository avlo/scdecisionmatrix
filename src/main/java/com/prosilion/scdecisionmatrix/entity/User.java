package com.prosilion.scdecisionmatrix.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "scmuser")
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String username;
  private String password;
  //  private boolean enabled;

  //	private Long satoshis;
  //	private Long reputation; // (f(contract_state(payer_state, payee_state, payout_time))

  @OneToOne(mappedBy = "user", optional = true)
  private Contract contract;

  //	@OneToOne(mappedBy = "user", optional = false) // TODO: change to ROLE, user_role table
  private String role;
}
