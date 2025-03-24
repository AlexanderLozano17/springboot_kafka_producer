package com.demo.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.core.entities.Mensaje;

public interface MensajeRepository extends JpaRepository<Mensaje, Long> {

}
