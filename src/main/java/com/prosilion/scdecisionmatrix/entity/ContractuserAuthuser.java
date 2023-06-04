package com.prosilion.scdecisionmatrix.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@Entity
public class ContractuserAuthuser implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer contractuserId;
	private String authuserName;

	public ContractuserAuthuser(@NonNull Integer contractuserId, @NonNull String authuserName) {
		this.contractuserId = contractuserId;
		this.authuserName = authuserName;
	}
}
