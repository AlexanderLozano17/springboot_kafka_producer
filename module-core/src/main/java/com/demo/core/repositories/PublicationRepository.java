package com.demo.core.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.core.entities.Publication;
import com.demo.dto.dto.PublicationDTO;

public interface PublicationRepository extends JpaRepository<Publication, Long> {
}
