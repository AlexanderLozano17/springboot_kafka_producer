package com.demo.core.services;

import java.util.List;
import java.util.Optional;

import com.demo.core.entities.Person;
import com.demo.dto.dto.PersonDTO;

public interface PersonService {
	
	/**
	 * 
	 * @param person
	 * @return
	 */
	Optional<PersonDTO> createPerson(Person person);
	
	/**
	 * 
	 * @param personaId
	 * @return
	 */
	Optional<PersonDTO> getPersonWithPublications(Long personId);
	
	/**
	 * 
	 * @return
	 */
	List<Person> getAllPeopleWithPublications();
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Optional<PersonDTO> getPersonBasicDetails(Long id);
	
	/**
	 * 
	 * @return
	 */
	List<PersonDTO> getAllPersons();

	/**
	 * 
	 * @param id
	 * @return
	 */
	boolean deletePersonById(Long id);
	
}
