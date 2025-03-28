package com.demo.core.services;

import java.util.List;
import java.util.Optional;

import com.demo.core.dto.PersonaDTO;
import com.demo.core.entities.Persona;

public interface PersonaService {

	Optional<Persona> guardar(Persona persona);
	
	Optional<Persona> obtenerPersonaIdPublicaciones(Long personaId);
	
	List<Persona> obtenerPersonaPublicaciones();
	
	Optional<PersonaDTO> obtenerId(Long id);
	
	List<PersonaDTO> obtener();

	boolean eliminarId(Long id);
	
}
