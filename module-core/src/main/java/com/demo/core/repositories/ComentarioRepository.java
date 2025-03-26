package com.demo.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.core.entities.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

}
