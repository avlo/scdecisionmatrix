package com.prosilion.scdecisionmatrix.repository;

import com.prosilion.scdecisionmatrix.model.entity.Contract;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer> {
  @Query("SELECT c FROM Contract c WHERE c.appUserId = :appUserId")
  List<Contract> getContractsByAppUserId(@NonNull Integer appUserId);

  @Query("SELECT c FROM Contract c WHERE c.appUserId <> :appUserId")
  List<Contract> getAvailableOppositeRoleContracts(@NonNull Integer appUserId);

  @Query("SELECT c FROM Contract c WHERE c.id = :id")
  Optional<Contract> getContractById(@NonNull Integer id);
}
