package com.demo.core.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import com.demo.utils.LogHelper;
import com.demo.utils.LogMessageKafka;
import com.demo.utils.LogPerson;
import com.demo.utils.LogPublication;

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
		logger.info(LogHelper.start(getClass(), "save"));
		try {
			Person savePerson = personRepository.save(person);	
			logger.info(LogHelper.success(getClass(), "save", String.format(LogPublication.PUBLICATION_SAVE_SUCCESS, savePerson.getId())));
			
			PersonDTO personDTO = personDTO(savePerson);
			producerService.sendMessageRecordPerson(new ResponseKafka(String.format(LogPerson.PERSON_SAVE_SUCCESS, savePerson.getId()), personDTO)); 		 
			 
			return Optional.of(personDTO); 	
			
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "save", String.format(LogPerson.PERSON_SAVE_ERROR, e.getMessage())), e);
			return Optional.empty();
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Person> getPersonsPublications(Long id) {
		logger.info(LogHelper.start(getClass(), "getPersonsPublications"));
		return personRepository.findById(id);
	}
	
	@Transactional(readOnly = true)
	public List<Person> getPersonsPublications() {
		logger.info(LogHelper.start(getClass(), "getPersonsPublications"));
		return personRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<PersonDTO> getAllPersons() {
		logger.info(LogHelper.start(getClass(), "getAllPersons"));
		return personRepository.getAllPerons();
	}

	@Override
	public Optional<PersonDTO> getPersonBasicById(Long id) { 
		logger.info(LogHelper.start(getClass(), "getPersonBasicById"));
		return personRepository.findPersonaBasicById(id);
	}
	
	@Override
	public boolean deleteById(Long id) {
		logger.info(LogHelper.start(getClass(), "deleteById"));
		
		if (!personRepository.existsById(id))  return false;		
		try {
			personRepository.deleteById(id);
			logger.info(LogHelper.success(getClass(), "deleteById", String.format(LogPerson.PERSON_DELETE_SUCCESS, id)));
			return true;
		} catch (Exception e) {
			logger.info(LogHelper.success(getClass(), "deleteById", String.format(LogPerson.PERSON_DELETE_ERROR, e.getMessage())), e);
			return false;
		}		
	}
	
	/**
	 * 
	 * @param persona
	 * @return
	 */
	private PersonDTO personDTO(Person person) {
		logger.info(LogHelper.start(getClass(), "personDTO"));
		return new PersonDTO(person.getId(),
				person.getNames(), 
				person.getLastNames(),
				person.getAge(), 
				person.getEmail(), 
				person.getTelephone()); 
	}
	
	/**
	 * 
	 * @param listPerson
	 * @return
	 */
	private List<PersonDTO> getPersonDTO(List<Person> listPerson) {
		logger.info(LogHelper.start(getClass(), "getPersonDTO"));
		return listPerson.stream().map(person -> personDTO(person)).collect(Collectors.toList());
	}
	
}
