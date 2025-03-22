package com.demo.services;

import java.util.List;
import java.util.Optional;

import com.demo.entities.Comentario;
import com.demo.entities.Persona;

public interface PersonaService {

	Optional<Persona> guardar(Persona persona);
	
	Optional<Persona> obtenerPersonaPublicaciones(Long id);
	
	Optional<Persona> obtenerPersona(Long id);
	
	List<Persona> obtenerTodo();

}
