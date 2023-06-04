package com.prosilion.scdecisionmatrix.repository;

import com.prosilion.scdecisionmatrix.entity.ContractuserAuthuser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractuserAuthuserRepository extends JpaRepository<ContractuserAuthuser, Integer> {
	//	@Query("SELECT e FROM ContractuserAuthuser e WHERE e.contractuserId = :contractuserId")
	Optional<ContractuserAuthuser> findByContractuserId(Integer contractuserId);

	//	@Query("SELECT e FROM ContractuserAuthuser e WHERE e.contractuserId = :contractuserId")
	Optional<ContractuserAuthuser> findByAuthuserName(String authuserName);

//	@Modifying(clearAutomatically = true)
//	@Query("UPDATE EscalationRequest set escalationId = :escalationId WHERE requestId = :requestId")
//	Integer update(Integer escalationId, Integer requestId);
}
