package com.demo.services;

import java.util.List;
import java.util.Optional;

import com.demo.dto.PublicacionDTO;
import com.demo.entities.Persona;
import com.demo.entities.Publicacion;

public interface PublicacionService {
	
	Optional<Publicacion> guardar(Publicacion publicacion);
	
	Optional<PublicacionDTO> obtenerPublicacionId(Long id);
	
	List<PublicacionDTO> obtenerPublicaciones();
	
	Optional<Publicacion> obtenerPublicacionPersona(Long id);

}
