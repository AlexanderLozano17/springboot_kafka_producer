package com.demo.core.services;

import java.util.List;
import java.util.Optional;

import com.demo.core.entities.Person;
import com.demo.dto.dto.PersonDTO;

public interface PersonService {
	
	Optional<PersonDTO> createPerson(Person person);
	
	Optional<PersonDTO> getPersonWithPublications(Long personaId);
	
	List<Person> getAllPeopleWithPublications();
	
	Optional<PersonDTO> getPersonBasicDetails(Long id);
	
	List<PersonDTO> getAllPersons();

	boolean deletePersonById(Long id);
	
}
