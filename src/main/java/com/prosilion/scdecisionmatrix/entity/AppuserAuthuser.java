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
public class AppuserAuthuser implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer appuserId;
	private String authuserName;

	public AppuserAuthuser(@NonNull Integer appuserId, @NonNull String authuserName) {
		this.appuserId = appuserId;
		this.authuserName = authuserName;
	}
}
