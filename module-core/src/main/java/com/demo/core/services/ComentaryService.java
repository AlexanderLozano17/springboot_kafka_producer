package com.demo.core.services;

import java.util.List;
import java.util.Optional;

import com.demo.core.entities.Comentary;

public interface ComentaryService {

	Optional<Comentary> save(Comentary comentario);
	
	Optional<Comentary> getComentaryById(Long id);
	
	List<Comentary> getComentaries();
	
	boolean deleteById(Long id);
	
}
