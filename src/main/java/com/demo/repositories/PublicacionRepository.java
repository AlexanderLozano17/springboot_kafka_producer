package com.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.dto.PublicacionDTO;
import com.demo.entities.Publicacion;

public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {

	
	@Query("SELECT new com.demo.dto.PublicacionDTO(p.id, p.titulo, p.contenido, p.fechaPublicacion) FROM Publicacion p")
	List<PublicacionDTO> obtenerPublicaciones();
	
	@Query("SELECT new com.demo.dto.PublicacionDTO(p.id, p.titulo, p.contenido, p.fechaPublicacion) FROM Publicacion p WHERE p.id = :id")
	Optional<PublicacionDTO> obtenerPublicacionId(@Param("id") Long id);
}
