package com.prosilion.scdecisionmatrix.repository;

import com.prosilion.scdecisionmatrix.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Bank, Integer> { }
