package it.fabrick.testapp.service;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.fabrick.testapp.domain.Transazione;
import it.fabrick.testapp.repository.TransazioneRepository;

@Service
@Transactional
public class TransazioneServiceImpl implements TransazioneService {

	@Autowired
	TransazioneRepository repo;
	
	@Override
	public void InsTutte(Set<Transazione> transazioni) 
	{
		repo.saveAll(transazioni);
		repo.flush();
	}

}
