package com.prosilion.scdecisionmatrix.repository;

import com.prosilion.scdecisionmatrix.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Integer> { }
