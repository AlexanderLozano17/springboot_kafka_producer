package com.demo.core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.core.entities.Comentario;
import com.demo.core.entities.Persona;


public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

	List<Comentario> findByPersona(Persona persona);
}
