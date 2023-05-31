package com.prosilion.scdecisionmatrix.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="appuser")
public class AppUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long satoshis;
	private Long reputation; // (f(contract_state(payer_state, payee_state, payout_time))

	@OneToOne(mappedBy = "appUser", optional = false)
	private Contract contract;
}
