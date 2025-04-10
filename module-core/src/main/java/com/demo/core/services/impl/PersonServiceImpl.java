package com.demo.core.services.impl;

import java.util.ArrayList;
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
import com.demo.dto.dto.PersonWithPublicationsDTO;
import com.demo.dto.dto.PublicationDTO;
import com.demo.dto.dto.ResponseKafka;
import com.demo.producer.services.KafkaProducerPersonService;
import com.demo.utils.LogHelper;
import com.demo.utils.LogPerson;

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
	@Transactional
	public Optional<PersonDTO> createPerson(Person person) {
		logger.info(LogHelper.start(getClass(), "createPerson"));
		try {
			Person savePerson = personRepository.save(person);	
			String message = String.format(LogPerson.PERSON_SAVE_SUCCESS, savePerson.getId());
			logger.info(LogHelper.success(getClass(), "createPerson", message));
			
			PersonDTO personDTO = personDTO(savePerson);
			producerService.sendMessageRecordPerson(new ResponseKafka(message, personDTO)); 		 
			 
			return Optional.of(personDTO); 	
			
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "createPerson", String.format(LogPerson.PERSON_SAVE_ERROR, e.getMessage())), e);
			return Optional.empty();
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<PersonDTO> getPersonById(Long id) {
		logger.info(LogHelper.start(getClass(), "getPersonById"));		
				
		Optional<Person> person = personRepository.findById(id);
		PersonDTO personDTO = personDTO(person.get());
		
		if (person.isPresent()) {
			logger.info(LogHelper.success(getClass(), "getPersonById", String.format(LogPerson.PERSON_FOUND, id)));
			
		} else {
			logger.warn(LogHelper.warn(getClass(), "getPersonById", String.format(LogPerson.PERSON_NOT_FOUND, id)));
		}
		logger.info(LogHelper.end(getClass(), "getPersonById"));
		return Optional.of(personDTO);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<PersonWithPublicationsDTO> getPersonWithPublications(Long id) {
		logger.info(LogHelper.start(getClass(), "getPersonWithPublications"));
		
		Optional<Person> personWithPublications = personRepository.findById(id);
		
		PersonWithPublicationsDTO personWithPublicationsDTO = personWithPublicationsDTO(personWithPublications.get());
		
		return Optional.of(personWithPublicationsDTO);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<PersonWithPublicationsDTO> getAllPeopleWithPublications() {
		logger.info(LogHelper.start(getClass(), "getAllPeopleWithPublications"));
		
		List<Person> listPerson = personRepository.findAll();
		
		List<PersonWithPublicationsDTO> personWithPublicationsDTOs = listPerson.stream()
				.map(personWithPublications -> personWithPublicationsDTO(personWithPublications))
				.collect(Collectors.toList());		
		
		if (!listPerson.isEmpty()) {
			logger.info(LogHelper.success(getClass(), "getAllPeopleWithPublications", String.format(LogPerson.PERSON_LIST_SUCCESS, personWithPublicationsDTOs.size())));
		} else {
			logger.warn(LogHelper.warn(getClass(), "getAllPeopleWithPublications", LogPerson.PERSON_NOT_CONTENT));
		}
		return personWithPublicationsDTOs;
	}

	@Override
	@Transactional(readOnly = true)
	public List<PersonDTO> getAllPersons() {
		logger.info(LogHelper.start(getClass(), "getAllPersons"));
		
		List<Person> listPerson = personRepository.findAll();
		List<PersonDTO> listPersonDTOs = getListPersonDTO(listPerson);
		if (!listPerson.isEmpty()) {
			logger.info(LogHelper.success(getClass(), "getAllPersons", String.format(LogPerson.PERSON_LIST_SUCCESS, listPerson.size())));
			
		} else {
			logger.warn(LogHelper.warn(getClass(), "getAllPersons", LogPerson.PERSON_NOT_CONTENT));
		}
		logger.info(LogHelper.end(getClass(), "getAllPersons"));	
		return listPersonDTOs;
	}
	
	@Override
	@Transactional
	public boolean deletePersonById(Long id) {
		logger.info(LogHelper.start(getClass(), "deletePersonById"));
		
		if (!personRepository.existsById(id)) {
			logger.warn(LogHelper.warn(getClass(), "deletePersonById", String.format(LogPerson.PERSON_NOT_FOUND, id)));
			return false;		
		}

		personRepository.deleteById(id);
		logger.info(LogHelper.success(getClass(), "deletePersonById", String.format(LogPerson.PERSON_DELETE_SUCCESS, id)));
		return true;			
	}
	
	/**
	 * 
	 * @param persona
	 * @return
	 */
	private PersonDTO personDTO(Person person) {
		logger.info(LogHelper.start(getClass(), "personDTO"));
		if (person == null) return null;
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
	private List<PersonDTO> getListPersonDTO(List<Person> listPerson) {
		logger.info(LogHelper.start(getClass(), "getPersonDTO"));
		if (listPerson.size() == 0) return new ArrayList<PersonDTO>(); 
		return listPerson.stream().map(person -> personDTO(person)).collect(Collectors.toList());
	}
	
	/**
	 * 
	 * @param person
	 * @return
	 */
	private PersonWithPublicationsDTO personWithPublicationsDTO(Person person) {
		logger.info(LogHelper.start(getClass(), "PersonWithPublicationsDTO"));
		
		if (person == null) return null;
		
		List<PublicationDTO> listPublicationsDTO = person.getPublications().stream()
				.map(publication -> new PublicationDTO(publication.getId(),
						publication.getTitle(), 
						publication.getContent(), 
						publication.getDatePublication())).collect(Collectors.toList());
		
		PersonWithPublicationsDTO personWithPublicationsDTO = new PersonWithPublicationsDTO(person.getId(), 
				person.getNames(), 
				person.getLastNames(),
				person.getAge(), 
				person.getEmail(), 
				person.getTelephone(),
				listPublicationsDTO);
		
		return personWithPublicationsDTO;
	}
}
