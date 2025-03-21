package com.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entities.Mensaje;

public interface MensajeRepository extends JpaRepository<Mensaje, Long> {

}
