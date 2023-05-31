package com.prosilion.scdecisionmatrix.repository;

import com.prosilion.scdecisionmatrix.entity.AppUser;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Integer> {
	AppUser findById(@NonNull Long id);
}
