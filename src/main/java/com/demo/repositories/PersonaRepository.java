package com.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.entities.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
	
	@Query("SELECT new Persona(p.id, p.nombres, p.apellidos, p.edad, p.email, p.telefono) FROM Persona p WHERE p.id = :id")
	Optional<Persona> findPersonaBasicById(@Param("id") Long id);

}
