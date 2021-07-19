package it.fabrick.testapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.fabrick.testapp.domain.Transazione;

@Repository
public interface TransazioneRepository extends JpaRepository<Transazione, Long> {}
