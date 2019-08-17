package pe.rodcar.busquedapersona.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import pe.rodcar.busquedapersona.entities.Persona;
import pe.rodcar.busquedapersona.service.PersonaService;

@RestController
@RequestMapping("/personas")
@Api(value = "REST de información de persona")
public class PersonaController {

	@Autowired
	private PersonaService personaService;
	
	@ApiOperation("Lista de persona")
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Persona>> fetchPersonaes() {
		try {
			List<Persona> personaes = new ArrayList<>();
			personaes = personaService.findAll();
			return new ResponseEntity<List<Persona>>(personaes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Persona>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Obtener persona por id")
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Persona> fetchPersona(@PathVariable("id") Long id) {
		try {
			Optional<Persona> persona = personaService.findById(id);

			if (!persona.isPresent()) {
				return new ResponseEntity<Persona>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Persona>(persona.get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Persona>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@ApiOperation("Registro persona")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> savePersona(@Valid @RequestBody Persona persona) {
		try {
			Persona c = new Persona();
			c = personaService.save(persona);

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(c.getId())
					.toUri();
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*@ApiOperation("Actualización de información de un persona")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updatePersona(@Valid @RequestBody Persona persona) {
		try {
			personaService.update(persona);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Eliminar persona por id")
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deletePersona(@PathVariable("id") Long id) {
		try {
			Optional<Persona> persona = personaService.findById(id);

			if (!persona.isPresent()) {
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			} else {
				personaService.deleteById(id);
				return new ResponseEntity<>("El persona se elimino", HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}*/
}
