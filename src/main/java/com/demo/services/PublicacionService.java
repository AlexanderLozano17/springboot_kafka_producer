package com.demo.services;

import java.util.List;
import java.util.Optional;

import com.demo.entities.Publicacion;

public interface PublicacionService {
	
	Optional<Publicacion> guardar(Publicacion publicacion);
	
	Optional<Publicacion> obtenerId(Long id);
	
	List<Publicacion> obtenerTodo();

}
