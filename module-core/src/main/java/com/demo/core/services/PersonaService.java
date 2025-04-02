package com.demo.core.services;

import java.util.List;
import java.util.Optional;

import com.demo.core.entities.Persona;
import com.demo.dto.dto.PersonaDTO;

public interface PersonaService {
	
	Optional<PersonaDTO> guardar(Persona persona);
	
	Optional<Persona> obtenerPersonaIdPublicaciones(Long personaId);
	
	List<Persona> obtenerPersonaPublicaciones();
	
	Optional<PersonaDTO> obtenerId(Long id);
	
	List<PersonaDTO> obtener();

	boolean eliminarId(Long id);
	
}
