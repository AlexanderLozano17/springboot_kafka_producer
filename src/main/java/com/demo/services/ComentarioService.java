package com.demo.services;

import java.util.List;
import java.util.Optional;

import com.demo.entities.Comentario;

public interface ComentarioService {

	Optional<Comentario> guardar(Comentario comentario);
	
	Optional<Comentario> obtenerId(Long id);
	
	List<Comentario> obtenerTodo();
	
}
