package com.prosilion.scdecisionmatrix.repository;

import com.prosilion.scdecisionmatrix.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer> { }
