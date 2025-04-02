package com.demo.core.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.core.entities.Person;
import com.demo.dto.dto.PersonDTO;

public interface PersonRepository extends JpaRepository<Person, Long> {
	
	@Query("SELECT new com.demo.dto.dto.PersonDTO(p.id, p.names, p.lastNames, p.age, p.email, p.telephone) FROM Person p WHERE p.id = :id")
	Optional<PersonDTO> findPersonaBasicById(@Param("id") Long id);

	@Query("SELECT new com.demo.dto.dto.PersonDTO(p.id, p.names, p.lastNames, p.age, p.email, p.telephone) FROM Person p")
	List<PersonDTO> getAllPerons();

}
