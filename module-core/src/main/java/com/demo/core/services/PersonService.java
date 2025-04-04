package com.demo.core.services;

import java.util.List;
import java.util.Optional;

import com.demo.core.entities.Person;
import com.demo.dto.dto.PersonDTO;

public interface PersonService {
	
	Optional<PersonDTO> save(Person person);
	
	Optional<Person> getPersonsPublications(Long personaId);
	
	List<Person> getPersonsPublications();
	
	Optional<PersonDTO> getPersonBasicById(Long id);
	
	List<PersonDTO> getAllPersons();

	boolean deleteById(Long id);
	
}
