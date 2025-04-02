package com.demo.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.core.entities.Comentary;

public interface ComentaryRepository extends JpaRepository<Comentary, Long> {

}
