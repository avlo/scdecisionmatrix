package com.prosilion.scdecisionmatrix.repository;

import com.prosilion.scdecisionmatrix.entity.ContractUser;
import java.util.Optional;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractUserRepository extends JpaRepository<ContractUser, Integer> {
	Optional<ContractUser> findById(Long id);
}
