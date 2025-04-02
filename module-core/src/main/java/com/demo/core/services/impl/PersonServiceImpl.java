package com.demo.core.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.core.entities.Person;
import com.demo.core.repositories.PersonRepository;
import com.demo.core.services.PersonService;
import com.demo.dto.dto.PersonDTO;
import com.demo.dto.dto.ResponseKafka;
import com.demo.producer.services.KafkaProducerPersonService;

@Service
public class PersonServiceImpl implements PersonService {
	
	private final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

	private final PersonRepository personRepository;
	
	private final KafkaProducerPersonService producerService;
		
	public PersonServiceImpl(PersonRepository personRepository, KafkaProducerPersonService producerService) {
		this.personRepository = personRepository;
		this.producerService = producerService;
	}
	
	@Override
	public Optional<PersonDTO> save(Person person) {
		// TODO Auto-generated method stub
		try {
			Person savePerson = personRepository.save(person);	
			PersonDTO personDTO = personDTO(savePerson);
			
			String mensaje = String.format("✅ Persona registrada con éxito (ID: %d, Título: %s)", savePerson.getId(), savePerson.getNames() + " " + savePerson.getLastNames());

			producerService.sendMessagePerson(new ResponseKafka(mensaje, personDTO)); 		 
			 
			return Optional.of(personDTO); 	
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("❌ Error al guardar la persona: {}", e.getMessage(), e);
			return Optional.empty();
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Person> getPersonsPublications(Long id) {
		// TODO Auto-generated method stub
		return personRepository.findById(id);
	}
	
	@Transactional(readOnly = true)
	public List<Person> getPersonsPublications() {
		// TODO Auto-generated method stub	
		return personRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<PersonDTO> getAllPerons() {
		// TODO Auto-generated method stub
		return personRepository.getAllPerons();
	}

	@Override
	public Optional<PersonDTO> gerPersonBasicById(Long id) {
		// TODO Auto-generated method stub
		return personRepository.findPersonaBasicById(id);
	}
	
	@Override
	public boolean deleteById(Long id) {
		
		if (!personRepository.existsById(id))  return false;		
		try {
			personRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error al eliminar el registro con ID {}", id, e.getMessage(), e);
			return false;
		}		
	}
	
	/**
	 * 
	 * @param persona
	 * @return
	 */
	private PersonDTO personDTO(Person person) {
		return new PersonDTO(person.getId(),
				person.getNames(), 
				person.getLastNames(),
				person.getAge(), 
				person.getEmail(), 
				person.getTelephone()); 
	}
	
}
