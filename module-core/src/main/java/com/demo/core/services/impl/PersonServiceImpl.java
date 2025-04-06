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
import com.demo.dto.dto.PublicationDTO;
import com.demo.dto.dto.ResponseKafka;
import com.demo.producer.services.KafkaProducerPersonService;
import com.demo.utils.LogHelper;
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
	public Optional<PersonDTO> getPersonWithPublications(Long id) {
		logger.info(LogHelper.start(getClass(), "getPersonWithPublications"));
		
		Optional<Person> personWithPublications = personRepository.findById(id);
		
		PersonDTO personDTO = new  PersonDTO(personWithPublications.get().getId(), 
				personWithPublications.get().getNames(), 
				personWithPublications.get().getLastNames(),
				personWithPublications.get().getAge(), 
				personWithPublications.get().getEmail(), 
				personWithPublications.get().getTelephone());
		
		List<PublicationDTO> listPublicationsDTO = personWithPublications.get().getPublications().stream()
				.map(publication -> new PublicationDTO(publication.getId(),
						publication.getTitle(), 
						publication.getContent(), 
						publication.getDatePublication())).collect(Collectors.toList());
		
		personDTO.setPublications(listPublicationsDTO);	
		
		return Optional.of(personDTO);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Person> getAllPeopleWithPublications() {
		logger.info(LogHelper.start(getClass(), "getAllPeopleWithPublications"));
		return personRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<PersonDTO> getAllPersons() {
		logger.info(LogHelper.start(getClass(), "getAllPersons"));
		return personRepository.getAllPerons();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<PersonDTO> getPersonBasicDetails(Long id) { 
		logger.info(LogHelper.start(getClass(), "getPersonBasicDetails"));
		return personRepository.findPersonBasicById(id);
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
