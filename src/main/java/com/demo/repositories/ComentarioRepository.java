package com.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entities.Comentario;
import com.demo.entities.Persona;


public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

	List<Comentario> findByPersona(Persona persona);
}
