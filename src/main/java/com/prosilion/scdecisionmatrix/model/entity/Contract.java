package com.prosilion.scdecisionmatrix.model.entity;

import jakarta.persistence.*;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Contract {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String text;
  private Long payerStake, payeeStake, payoutAmount;
  private Boolean payerState, payeeState, completed;
  private Date agreedCompletionTime;

  private CreatorRoleEnum creatorRole;

  private Integer appUserId;
  private Integer counterPartyId;
}
