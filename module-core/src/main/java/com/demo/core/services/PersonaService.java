package com.demo.core.services;

import java.util.List;
import java.util.Optional;

import com.demo.core.dto.PersonaDTO;
import com.demo.core.entities.Persona;

public interface PersonaService {

	Optional<Persona> guardar(Persona persona);
	
	Optional<Persona> obtenerPersonaPublicaciones(Long personaId);
	
	Optional<PersonaDTO> obtenerPersonaId(Long id);
	
	List<PersonaDTO> obtenerPersonas();

	boolean eliminarId(Long id);
	
}
