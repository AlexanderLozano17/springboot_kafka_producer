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
import com.demo.dto.dto.PersonWithPublicationsDTO;
import com.demo.dto.dto.PublicationDTO;
import com.demo.dto.dto.ResponseApi;
import com.demo.main.utils.ApiMessages;
import com.demo.utils.LogHelper;
import com.demo.utils.LogPerson;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/person")
@Tag(name = "Person", description = "Operaciones relacionadas con personas")
public class PersonController {
	
	private final Logger logger = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	private PersonService personService;
	
	@Operation(
		    summary = "Crear una persona",
		    description = "Crea una nueva persona con los datos proporcionados.",
		    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
		        description = "Objeto de persona que contiene datos relacionados.",
		        required = true,
		        content = @Content(
		            schema = @Schema(implementation = PersonDTO.class)
		        )
		    ),
		    responses = {
		        @ApiResponse(responseCode = "201", description = ApiMessages.SAVE_SUCCESS, content = @Content(schema = @Schema(implementation = ResponseApi.class))),
		        @ApiResponse(responseCode = "500", description = ApiMessages.INTERNAL_SERVER_ERROR, content = @Content(schema = @Schema(implementation = ResponseApi.class)))
		    }
		)
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

	@Operation(
		    summary = "Obtener persona por ID",
		    description = "Devuelve una persona específica por su ID.",
		    responses = {
		        @ApiResponse(responseCode = "200", description = ApiMessages.RECORD_FOUND, content = @Content(schema = @Schema(implementation = ResponseApi.class))),
		        @ApiResponse(responseCode = "404", description = ApiMessages.RECORD_NOT_FOUND, content = @Content(schema = @Schema(implementation = ResponseApi.class))),
		        @ApiResponse(responseCode = "500", description = ApiMessages.INTERNAL_SERVER_ERROR, content = @Content(schema = @Schema(implementation = ResponseApi.class)))
		    }
		)
	@GetMapping("/{id}")
	public ResponseEntity<ResponseApi<PersonDTO>> getPersonById(@PathVariable Long id) {
		 logger.info(LogHelper.start(getClass(), "getPersonById"));
		 
		try {
			Optional<PersonDTO> person = personService.getPersonById(id);
			if (person.isPresent()) {
				logger.info(LogHelper.success(getClass(), "getPersonById", String.format(LogPerson.PERSON_FOUND, id)));
				logger.info(LogHelper.end(getClass(), "getPersonById"));
				return ResponseEntity.ok(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_FOUND, person.get()));
			} 
			
			logger.warn(LogHelper.warn(getClass(), "getPersonById", String.format(LogPerson.PERSON_NOT_FOUND, id)));
			logger.info(LogHelper.end(getClass(), "getPersonById"));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_NOT_FOUND, null));
		
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "getPersonById", e.getMessage()), e);
			logger.info(LogHelper.end(getClass(), "getPersonById"));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseApi(ApiMessages.ERROR, ApiMessages.INTERNAL_SERVER_ERROR, null));
		}
	}
	
	@Operation(
		    summary = "Obtiene un listado de personas",
		    responses = {
		        @ApiResponse(responseCode = "200", description = ApiMessages.RECORD_FOUND, content = @Content(schema = @Schema(implementation = ResponseApi.class))),
		        @ApiResponse(responseCode = "404", description = ApiMessages.RECORD_NOT_FOUND, content = @Content(schema = @Schema(implementation = ResponseApi.class))),
		        @ApiResponse(responseCode = "500", description = ApiMessages.INTERNAL_SERVER_ERROR, content = @Content(schema = @Schema(implementation = ResponseApi.class)))
		    }
		)
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
		
	@Operation(
		    summary = "Obtener una persona con sus publicaciones",
		    responses = {
		        @ApiResponse(responseCode = "200", description = ApiMessages.RECORD_FOUND, content = @Content(schema = @Schema(implementation = ResponseApi.class))),
		        @ApiResponse(responseCode = "404", description = ApiMessages.RECORD_NOT_FOUND, content = @Content(schema = @Schema(implementation = ResponseApi.class))),
		        @ApiResponse(responseCode = "500", description = ApiMessages.INTERNAL_SERVER_ERROR, content = @Content(schema = @Schema(implementation = ResponseApi.class)))
		    }
		)
	@GetMapping("/{personId}/publications")
	public ResponseEntity<ResponseApi<PersonWithPublicationsDTO>> getPersonWithPublications(@PathVariable Long personId) {
		logger.info(LogHelper.start(getClass(), "getPersonWithPublications"));
		
		try {			
			Optional<PersonWithPublicationsDTO> personPublicationDTO = personService.getPersonWithPublications(personId);		
			if (personPublicationDTO.isPresent()) {

				logger.info(LogHelper.success(getClass(), "getPersonWithPublications", String.format(LogPerson.PERSON_FOUND, personId)));
				logger.info(LogHelper.end(getClass(), "getPersonWithPublications"));
				return ResponseEntity.ok(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_FOUND, personPublicationDTO.get()));	
			}
			
			logger.warn(LogHelper.warn(getClass(), "getPersonWithPublications", String.format(LogPerson.PERSON_NOT_FOUND, personId)));
			logger.info(LogHelper.end(getClass(), "getPersonWithPublications"));
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.NO_CONTENT, null));
			
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "getPersonWithPublications", LogPerson.PERSON_LIST_ERROR));
			logger.info(LogHelper.end(getClass(), "getPersonWithPublications"));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseApi(ApiMessages.ERROR, ApiMessages.INTERNAL_SERVER_ERROR, null));
		}
	}
	
	@Operation(
		    summary = "Obtener un listado de personas con sus publicaciones",
		    responses = {
		        @ApiResponse(responseCode = "200", description = ApiMessages.RECORD_FOUND, content = @Content(schema = @Schema(implementation = ResponseApi.class))),
		        @ApiResponse(responseCode = "404", description = ApiMessages.RECORD_NOT_FOUND, content = @Content(schema = @Schema(implementation = ResponseApi.class))),
		        @ApiResponse(responseCode = "500", description = ApiMessages.INTERNAL_SERVER_ERROR, content = @Content(schema = @Schema(implementation = ResponseApi.class)))
		    }
		)
	@GetMapping("/publications")
	public ResponseEntity<ResponseApi<List<PersonWithPublicationsDTO>>> getAllPeopleWithPublications() {
		logger.info(LogHelper.start(getClass(), "getAllPeopleWithPublications"));
		
		try {			
			List<PersonWithPublicationsDTO> personPublications = personService.getAllPeopleWithPublications();			
			if (!personPublications.isEmpty()) {
				logger.info(LogHelper.success(getClass(), "getAllPeopleWithPublications", String.format(LogPerson.PERSON_LIST_SUCCESS, personPublications.size())));
				logger.info(LogHelper.end(getClass(), "getAllPeopleWithPublications"));
				return ResponseEntity.ok(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.LIST_SUCCESS, personPublications));
			}
			
			logger.warn(LogHelper.warn(getClass(), "getAllPeopleWithPublications", LogPerson.PERSON_NOT_FOUND));
			logger.info(LogHelper.end(getClass(), "getAllPeopleWithPublications"));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.NO_CONTENT, null));
			
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "getAllPeopleWithPublications", LogPerson.PERSON_LIST_ERROR));
			logger.info(LogHelper.end(getClass(), "getAllPeopleWithPublications"));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseApi(ApiMessages.ERROR, ApiMessages.INTERNAL_SERVER_ERROR, null));
		}
	}
	
	@Operation(
		    summary = "elimina una persona",
		    responses = {
		        @ApiResponse(responseCode = "200", description = ApiMessages.RECORD_FOUND, content = @Content(schema = @Schema(implementation = ResponseApi.class))),
		        @ApiResponse(responseCode = "404", description = ApiMessages.RECORD_NOT_FOUND, content = @Content(schema = @Schema(implementation = ResponseApi.class))),
		        @ApiResponse(responseCode = "500", description = ApiMessages.INTERNAL_SERVER_ERROR, content = @Content(schema = @Schema(implementation = ResponseApi.class)))
		    }
		)
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
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_NOT_FOUND, null));
			}
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "deletePersonById", LogPerson.PERSON_DELETE_ERROR));
			logger.info(LogHelper.end(getClass(), "deletePersonById"));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseApi(ApiMessages.ERROR, ApiMessages.INTERNAL_SERVER_ERROR, null));
		}
	}
}
