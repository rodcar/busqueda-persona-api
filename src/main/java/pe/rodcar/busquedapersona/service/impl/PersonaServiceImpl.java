package pe.rodcar.busquedapersona.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.rodcar.busquedapersona.entities.Persona;
import pe.rodcar.busquedapersona.repository.PersonaRepository;
import pe.rodcar.busquedapersona.service.PersonaService;

@Service
public class PersonaServiceImpl implements PersonaService {

	@Autowired
	private PersonaRepository personaRepository;
	
	@Override
	public List<Persona> findAll() throws Exception {
		return personaRepository.findAll();
	}

	@Override
	public Persona save(Persona t) throws Exception {
		return personaRepository.save(t);
	}

	@Override
	public Persona update(Persona t) throws Exception {
		return personaRepository.save(t);
	}

	@Override
	public void deleteById(Long id) throws Exception {
		personaRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		
	}

	@Override
	public Optional<Persona> findById(Long id) throws Exception {	
		return personaRepository.findById(id);
	}

}
