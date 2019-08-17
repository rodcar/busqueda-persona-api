package pe.rodcar.busquedapersona.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.rodcar.busquedapersona.entities.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long>{
	Optional<Persona> findById(Long id);
}
