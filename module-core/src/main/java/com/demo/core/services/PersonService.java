package com.demo.core.services;

import java.util.List;
import java.util.Optional;

import com.demo.core.entities.Person;
import com.demo.dto.dto.PersonDTO;
import com.demo.dto.dto.PersonWithPublicationsDTO;

public interface PersonService {
	
	/**
	 * 
	 * @param person
	 * @return
	 */
	Optional<PersonDTO> createPerson(Person person);
	
	/**
	 * 
	 * @return
	 */
	Optional<PersonDTO> getPersonById(Long id);
	
	/**
	 * 
	 * @param personaId
	 * @return
	 */
	Optional<PersonWithPublicationsDTO> getPersonWithPublications(Long personId);
	
	/**
	 * 
	 * @return
	 */
	List<PersonWithPublicationsDTO> getAllPeopleWithPublications();
		
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
