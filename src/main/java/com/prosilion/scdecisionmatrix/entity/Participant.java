package com.prosilion.scdecisionmatrix.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
public class Participant implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
//	private Long satoshis;
//	private Long reputation; // (f(contract_state(payer_state, payee_state, payout_time))

	@OneToOne(mappedBy = "participant", optional = true)
	private Contract contract;
}
