package com.demo.core.services;

import java.util.List;
import java.util.Optional;

import com.demo.core.entities.Publicacion;
import com.demo.dto.dto.PublicacionDTO;

public interface PublicacionService {
	
	Optional<Publicacion> guardar(Publicacion publicacion);
	
	Optional<PublicacionDTO> obtenerPublicacionId(Long id);
	
	List<PublicacionDTO> obtenerPublicaciones();
	
	Optional<Publicacion> obtenerPublicacionPersona(Long id);

}
