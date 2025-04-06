package com.demo.main.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.core.entities.Person;
import com.demo.core.entities.Publication;
import com.demo.core.services.PersonService;
import com.demo.dto.dto.PersonDTO;
import com.demo.dto.dto.PublicationDTO;
import com.demo.dto.dto.ResponseApi;
import com.demo.main.utils.ApiMessages;
import com.demo.utils.LogHelper;
import com.demo.utils.LogPerson;

@RestController
@RequestMapping("/api/persona")
public class PersonController {
	
	private final Logger logger = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	private PersonService personService;
	
	@PostMapping("/create")
    public ResponseEntity<ResponseApi<PersonDTO>> createPerson(@RequestBody Person person) {
        logger.info(LogHelper.start(getClass(), "createPerson"));

        try {
            Optional<PersonDTO> optionalPerson = personService.createPerson(person);            
            if (optionalPerson.isPresent()) {
                logger.info(LogHelper.success(getClass(), "createPerson", String.format(LogPerson.PERSON_SAVE_SUCCESS, optionalPerson.get().getId())));
                logger.info(LogHelper.end(getClass(), "createPerson"));
                return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.SAVE_SUCCESS, optionalPerson.get()));
            }             
        } catch (Exception e) {
            logger.error(LogHelper.error(getClass(), "createPerson", e.getMessage()), e);
        }
        logger.info(LogHelper.end(getClass(), "createPerson"));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseApi(ApiMessages.ERROR, ApiMessages.INTERNAL_SERVER_ERROR, null));
    }


	@GetMapping("/{id}")
	public ResponseEntity<ResponseApi<PersonDTO>> getPersonBasicDetails(@PathVariable Long id) {
		 logger.info(LogHelper.start(getClass(), "getPersonBasicDetails"));
		 
		try {
			Optional<PersonDTO> person = personService.getPersonBasicDetails(id);
			if (person.isPresent()) {
				logger.info(LogHelper.success(getClass(), "getPersonBasicDetails", String.format(LogPerson.PERSON_FOUND, id)));
				logger.info(LogHelper.end(getClass(), "getPersonBasicDetails"));
				return ResponseEntity.ok(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_FOUND, person.get()));
			} 
			
			logger.warn(LogHelper.warn(getClass(), "getPersonBasicDetails", String.format(LogPerson.PERSON_NOT_FOUND, id)));
			logger.info(LogHelper.end(getClass(), "getPersonBasicDetails"));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_NOT_FOUND, null));
		
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "getPersonBasicDetails", e.getMessage()), e);
			logger.info(LogHelper.end(getClass(), "getPersonBasicDetails"));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseApi(ApiMessages.ERROR, ApiMessages.INTERNAL_SERVER_ERROR, null));
		}
	}
	
	@GetMapping
	public ResponseEntity<ResponseApi<List<PersonDTO>>> getAllPersons() {
		 logger.info(LogHelper.start(getClass(), "getAllPerson"));
		 
		try {
			List<PersonDTO> personDTOs = personService.getAllPersons();
			if (!personDTOs.isEmpty()) {
				logger.info(LogHelper.success(getClass(), "getAllPerson", String.format(LogPerson.PERSON_LIST_SUCCESS, personDTOs.size())));
				logger.info(LogHelper.end(getClass(), "getAllPersons"));
				return ResponseEntity.ok(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.LIST_SUCCESS, personDTOs));
			} 
			
			logger.warn(LogHelper.warn(getClass(), "getAllPerson", String.format(LogPerson.PERSON_LIST_SUCCESS, 0)));
			logger.info(LogHelper.end(getClass(), "getAllPersons"));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_NOT_FOUND, null));
		
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "getAllPerson", e.getMessage()), e);
			logger.info(LogHelper.end(getClass(), "getAllPersons"));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseApi(ApiMessages.ERROR, ApiMessages.INTERNAL_SERVER_ERROR, null));
		}
	}
			
	@GetMapping("/{personId}/publicaciones")
	public ResponseEntity<ResponseApi<PersonDTO>> getPersonWithPublications(@PathVariable Long personId) {
		logger.info(LogHelper.start(getClass(), "getPersonWithPublications"));
		
		try {			
			Optional<PersonDTO> personPublicationDTO = personService.getPersonWithPublications(personId);			
			if (personPublicationDTO.isPresent()) {

				logger.info(LogHelper.success(getClass(), "getPersonWithPublications", String.format(LogPerson.PERSON_FOUND, personId)));
				logger.info(LogHelper.end(getClass(), "getPersonWithPublications"));
				return ResponseEntity.ok(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_FOUND, personPublicationDTO.get()));	
			}
			
			logger.warn(LogHelper.warn(getClass(), "getPersonWithPublications", String.format(LogPerson.PERSON_NOT_FOUND, personId)));
			logger.info(LogHelper.end(getClass(), "getPersonWithPublications"));
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.NO_CONTENT, null));
			
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "getPersonWithPublications", LogPerson.PERSON_LIST_ERROR));
			logger.info(LogHelper.end(getClass(), "getPersonWithPublications"));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseApi(ApiMessages.ERROR, ApiMessages.INTERNAL_SERVER_ERROR, null));
		}
	}
	
	@GetMapping("/publicaciones")
	public ResponseEntity<ResponseApi<List<Person>>> getAllPeopleWithPublications() {
		logger.info(LogHelper.start(getClass(), "getAllPeopleWithPublications"));
		
		try {			
			List<Person> personPublications = personService.getAllPeopleWithPublications();			
			if (!personPublications.isEmpty()) {
				logger.info(LogHelper.success(getClass(), "getAllPeopleWithPublications", String.format(LogPerson.PERSON_LIST_SUCCESS, personPublications.size())));
				logger.info(LogHelper.end(getClass(), "getAllPeopleWithPublications"));
				return ResponseEntity.ok(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.LIST_SUCCESS, personPublications));
			}
			
			logger.warn(LogHelper.warn(getClass(), "getAllPeopleWithPublications", LogPerson.PERSON_NOT_FOUND));
			logger.info(LogHelper.end(getClass(), "getAllPeopleWithPublications"));
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.NO_CONTENT, null));
			
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "getAllPeopleWithPublications", LogPerson.PERSON_LIST_ERROR));
			logger.info(LogHelper.end(getClass(), "getAllPeopleWithPublications"));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseApi(ApiMessages.ERROR, ApiMessages.INTERNAL_SERVER_ERROR, null));
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseApi<String>> deletePersonById(@PathVariable long id) {
		logger.info(LogHelper.start(getClass(), "deletePersonById"));
		
		try {
			if (personService.deletePersonById(id)) {
				logger.info(LogHelper.success(getClass(), "deletePersonById", String.format(LogPerson.PERSON_DELETE_SUCCESS, id)));
				logger.info(LogHelper.end(getClass(), "deletePersonById"));
				return ResponseEntity.ok(new ResponseApi(ApiMessages.SUCCESS, String.format(ApiMessages.DELETE_SUCCESS, id), null));
			} else {
				logger.error(LogHelper.error(getClass(), "deletePersonById", String.format(LogPerson.PERSON_NOT_FOUND, id)));
				logger.info(LogHelper.end(getClass(), "deletePersonById"));
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_NOT_FOUND, null));
			}
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "deletePersonById", LogPerson.PERSON_DELETE_ERROR));
			logger.info(LogHelper.end(getClass(), "deletePersonById"));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseApi(ApiMessages.ERROR, ApiMessages.INTERNAL_SERVER_ERROR, null));
		}
		
	}
}
