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
public class AppUserAuthUser implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer appUserId;
	private String authUserName;

	public AppUserAuthUser(@NonNull Integer appUserId, @NonNull String authUserName) {
		this.appUserId = appUserId;
		this.authUserName = authUserName;
	}
}
