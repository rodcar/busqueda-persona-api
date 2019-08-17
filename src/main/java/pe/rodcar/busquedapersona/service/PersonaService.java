package pe.rodcar.busquedapersona.service;

import java.util.Optional;

import pe.rodcar.busquedapersona.entities.Persona;

public interface PersonaService extends CrudService<Persona>{
	Optional<Persona> findById(Long id) throws Exception;
}
