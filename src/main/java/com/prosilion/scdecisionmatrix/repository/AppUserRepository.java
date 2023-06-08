package com.prosilion.scdecisionmatrix.repository;

import com.prosilion.scdecisionmatrix.entity.AppUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
	Optional<AppUser> findById(Long id);
}
