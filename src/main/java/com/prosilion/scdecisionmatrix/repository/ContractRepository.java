package com.prosilion.scdecisionmatrix.repository;

import com.prosilion.scdecisionmatrix.entity.Contract;
import java.util.List;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer> {
	@Query("SELECT c FROM Contract c WHERE c.contractUser.id = :userId")
	List<Contract> getContracts(@NonNull Integer userId);
}