package com.prosilion.scdecisionmatrix.service;

import com.prosilion.scdecisionmatrix.entity.Participant;
import com.prosilion.scdecisionmatrix.repository.ParticipantRepository;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParticipantService {
  private final ParticipantRepository participantRepository;

  @Autowired
  public ParticipantService(@NonNull ParticipantRepository participantRepository) {
    this.participantRepository = participantRepository;
  }

  public Optional<Participant> get(@NonNull Integer participantId) {
    return participantRepository.findById(participantId);
  }
  public Optional<Participant> get(@NonNull Participant participant) {
    return this.get(participant.getId());
  }
  @Transactional
  public Participant save(@NonNull Participant participant) {
    return participantRepository.save(participant);
  }
}
