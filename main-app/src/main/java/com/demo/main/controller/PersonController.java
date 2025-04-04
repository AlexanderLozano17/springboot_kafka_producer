package com.demo.main.controller;

import java.util.List;
import java.util.Optional;

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
import com.demo.core.services.PersonService;
import com.demo.dto.dto.PersonDTO;
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
	
	@PostMapping("/save")
    public ResponseEntity<ResponseApi<PersonDTO>> save(@RequestBody Person person) {
        logger.info(LogHelper.start(getClass(), "save"));

        try {
            Optional<PersonDTO> optionalPerson = personService.save(person);            
            if (optionalPerson.isPresent()) {
                logger.info(LogHelper.success(getClass(), "save", String.format(LogPerson.PERSON_SAVE_SUCCESS, optionalPerson.get().getId())));
                return ResponseEntity.ok(responseApi(ApiMessages.SUCCESS, ApiMessages.SAVE_SUCCESS, optionalPerson.get()));
            }             
        } catch (Exception e) {
            logger.error(LogHelper.error(getClass(), "save", e.getMessage()), e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseApi(ApiMessages.ERROR, ApiMessages.INTERNAL_SERVER_ERROR, null));
    }


	@GetMapping("/{id}/detalle-basico")
	public ResponseEntity<ResponseApi<PersonDTO>> gerPersonBasicById(@PathVariable Long id) {
		 logger.info(LogHelper.start(getClass(), "gerPersonBasicById"));
		 
		try {
			Optional<PersonDTO> person = personService.getPersonBasicById(id);
			if (person.isPresent()) {
				logger.info(LogHelper.success(getClass(), "gerPersonBasicById", String.format(LogPerson.PERSON_FOUND, id)));
				return ResponseEntity.ok(responseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_FOUND, person.get()));
			} 
			
			logger.warn(LogHelper.warn(getClass(), "gerPersonBasicById", String.format(LogPerson.PERSON_NOT_FOUND, id)));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_NOT_FOUND, null));
		
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "gerPersonBasicById", e.getMessage()), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseApi(ApiMessages.ERROR, ApiMessages.INTERNAL_SERVER_ERROR, null));
		}
	}
	
	@GetMapping
	public ResponseEntity<ResponseApi<List<PersonDTO>>> getAllPersons() {
		logger.info(LogHelper.start(getClass(), "getAllPersons"));
		
		try {			
			List<PersonDTO> listPerson = personService.getAllPersons();
			logger.info(LogHelper.success(getClass(), "getAllPersons", String.format(LogPerson.PERSON_LIST_SUCCESS, listPerson.size())));
			return ResponseEntity.ok(responseApi(ApiMessages.SUCCESS, ApiMessages.LIST_SUCCESS, listPerson));
		
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "gerPersonBasicById", e.getMessage()), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseApi(ApiMessages.ERROR, ApiMessages.INTERNAL_SERVER_ERROR, null));
		}
	}	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseApi<String>> deleteById(@PathVariable long id) {
		logger.info(LogHelper.start(getClass(), "deleteById"));
		
		boolean isDelete = personService.deleteById(id);
		if (isDelete) {
			logger.info(LogHelper.success(getClass(), "deleteById", String.format(LogPerson.PERSON_DELETE_SUCCESS, id)));
			return ResponseEntity.ok(responseApi(ApiMessages.SUCCESS, String.format(ApiMessages.DELETE_SUCCESS, id), null));
		} else {
			logger.error(LogHelper.error(getClass(), "deleteById", String.format(LogPerson.PERSON_DELETE_ERROR, id)));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseApi(ApiMessages.SUCCESS, ApiMessages.INTERNAL_SERVER_ERROR, null));
		}
	}
	
	@GetMapping("/{personaId}/publicaciones")
	public ResponseEntity<ResponseApi<Person>> getPersonIdPublications(@PathVariable Long personaId) {
		logger.info(LogHelper.start(getClass(), "getPersonIdPublications"));
		
		try {			
			Optional<Person> personPublication = personService.getPersonsPublications(personaId);
			
			if (personPublication.isPresent()) {
				logger.info(LogHelper.success(getClass(), "getPersonIdPublications", String.format(LogPerson.PERSON_FOUND, personaId)));
				return ResponseEntity.ok(responseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_FOUND, personPublication.get()));	
			}
			
			logger.warn(LogHelper.warn(getClass(), "getPersonIdPublications", String.format(LogPerson.PERSON_NOT_FOUND, personaId)));
			return ResponseEntity.status(HttpStatus.OK).body(responseApi(ApiMessages.SUCCESS, ApiMessages.NO_CONTENT, null));
			
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "getPersonIdPublications", LogPerson.PERSON_LIST_ERROR));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseApi(ApiMessages.ERROR, ApiMessages.INTERNAL_SERVER_ERROR, null));
		}
	}
	
	@GetMapping("/publicaciones")
	public ResponseEntity<ResponseApi<List<Person>>> getPersonsPublications() {
		logger.info(LogHelper.start(getClass(), "getPersonsPublications"));
		
		try {			
			List<Person> personPublications = personService.getPersonsPublications();
			
			if (!personPublications.isEmpty()) {
				logger.info(LogHelper.success(getClass(), "getPersonsPublications", String.format(LogPerson.PERSON_LIST_SUCCESS, personPublications.size())));
				return ResponseEntity.ok(responseApi(ApiMessages.SUCCESS, ApiMessages.LIST_SUCCESS, personPublications));
			}
			
			logger.warn(LogHelper.warn(getClass(), "getPersonsPublications", LogPerson.PERSON_NOT_FOUND));
			return ResponseEntity.status(HttpStatus.OK).body(responseApi(ApiMessages.SUCCESS, ApiMessages.NO_CONTENT, null));
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "getPersonsPublications", LogPerson.PERSON_LIST_ERROR));
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
