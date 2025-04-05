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
                return ResponseEntity.status(HttpStatus.CREATED).body(responseApi(ApiMessages.SUCCESS, ApiMessages.SAVE_SUCCESS, optionalPerson.get()));
            }             
        } catch (Exception e) {
            logger.error(LogHelper.error(getClass(), "createPerson", e.getMessage()), e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseApi(ApiMessages.ERROR, ApiMessages.INTERNAL_SERVER_ERROR, null));
    }


	@GetMapping("/{id}")
	public ResponseEntity<ResponseApi<PersonDTO>> getPersonBasicDetails(@PathVariable Long id) {
		 logger.info(LogHelper.start(getClass(), "getPersonBasicDetails"));
		 
		try {
			Optional<PersonDTO> person = personService.getPersonBasicDetails(id);
			if (person.isPresent()) {
				logger.info(LogHelper.success(getClass(), "getPersonBasicDetails", String.format(LogPerson.PERSON_FOUND, id)));
				return ResponseEntity.ok(responseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_FOUND, person.get()));
			} 
			
			logger.warn(LogHelper.warn(getClass(), "getPersonBasicDetails", String.format(LogPerson.PERSON_NOT_FOUND, id)));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_NOT_FOUND, null));
		
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "getPersonBasicDetails", e.getMessage()), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseApi(ApiMessages.ERROR, ApiMessages.INTERNAL_SERVER_ERROR, null));
		}
	}
			
	@GetMapping("/{personaId}/publicaciones")
	public ResponseEntity<ResponseApi<PersonDTO>> getPersonWithPublications(@PathVariable Long personaId) {
		logger.info(LogHelper.start(getClass(), "getPersonWithPublications"));
		
		try {			
			Optional<PersonDTO> personPublicationDTO = personService.getPersonWithPublications(personaId);			
			if (personPublicationDTO.isPresent()) {

				logger.info(LogHelper.success(getClass(), "getPersonWithPublications", String.format(LogPerson.PERSON_FOUND, personaId)));
				return ResponseEntity.ok(responseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_FOUND, personPublicationDTO.get()));	
			}
			
			logger.warn(LogHelper.warn(getClass(), "getPersonWithPublications", String.format(LogPerson.PERSON_NOT_FOUND, personaId)));
			return ResponseEntity.status(HttpStatus.OK).body(responseApi(ApiMessages.SUCCESS, ApiMessages.NO_CONTENT, null));
			
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "getPersonWithPublications", LogPerson.PERSON_LIST_ERROR));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseApi(ApiMessages.ERROR, ApiMessages.INTERNAL_SERVER_ERROR, null));
		}
	}
	
	@GetMapping("/publicaciones")
	public ResponseEntity<ResponseApi<List<Person>>> getAllPeopleWithPublications() {
		logger.info(LogHelper.start(getClass(), "getAllPeopleWithPublications"));
		
		try {			
			List<Person> personPublications = personService.getAllPeopleWithPublications();			
			if (!personPublications.isEmpty()) {
				logger.info(LogHelper.success(getClass(), "getAllPeopleWithPublications", String.format(LogPerson.PERSON_LIST_SUCCESS, personPublications.size())));
				return ResponseEntity.ok(responseApi(ApiMessages.SUCCESS, ApiMessages.LIST_SUCCESS, personPublications));
			}
			
			logger.warn(LogHelper.warn(getClass(), "getAllPeopleWithPublications", LogPerson.PERSON_NOT_FOUND));
			return ResponseEntity.status(HttpStatus.OK).body(responseApi(ApiMessages.SUCCESS, ApiMessages.NO_CONTENT, null));
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "getAllPeopleWithPublications", LogPerson.PERSON_LIST_ERROR));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseApi(ApiMessages.ERROR, ApiMessages.INTERNAL_SERVER_ERROR, null));
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseApi<String>> deletePersonById(@PathVariable long id) {
		logger.info(LogHelper.start(getClass(), "deletePersonById"));
		
		try {
			if (personService.deletePersonById(id)) {
				logger.info(LogHelper.success(getClass(), "deletePersonById", String.format(LogPerson.PERSON_DELETE_SUCCESS, id)));
				return ResponseEntity.ok(responseApi(ApiMessages.SUCCESS, String.format(ApiMessages.DELETE_SUCCESS, id), null));
			} else {
				logger.error(LogHelper.error(getClass(), "deletePersonById", String.format(LogPerson.PERSON_NOT_FOUND, id)));
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_NOT_FOUND, null));
			}
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "deletePersonById", LogPerson.PERSON_DELETE_ERROR));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseApi(ApiMessages.ERROR, ApiMessages.INTERNAL_SERVER_ERROR, null));
		}
		
	}
		
	/**
	 * 
	 * @param <T>
	 * @param statusCode
	 * @param message
	 * @param dataObject
	 * @return
	 */
	private <T> ResponseApi<T> responseApi(String status, String message, T dataObject) {
		logger.info(LogHelper.start(getClass(), "responseApi"));
		return new ResponseApi(status, message, dataObject);
	}
		
}
