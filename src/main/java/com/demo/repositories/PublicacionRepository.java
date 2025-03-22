package com.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entities.Publicacion;

public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {

}
