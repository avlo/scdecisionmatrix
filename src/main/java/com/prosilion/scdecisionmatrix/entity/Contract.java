package com.prosilion.scdecisionmatrix.entity;

import jakarta.persistence.*;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Contract {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String text;
	private Long payerStake, payeeStake, payoutAmount;
	private Boolean payerState, payeeState, completed;
	private Date agreedCompletionTime;

	@OneToOne(cascade = CascadeType.MERGE, optional = false)
	@JoinTable(
			name = "contract_contractuser",
			joinColumns = {@JoinColumn(name = "contract_id", referencedColumnName = "id")},
			inverseJoinColumns = {
					@JoinColumn(name = "contractuser_id", referencedColumnName = "id", nullable = false)
			})
	private ContractUser contractUser;
}
