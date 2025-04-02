package com.demo.main.controller;

import java.util.List;
import java.util.Optional;

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

@RestController
@RequestMapping("/api/persona")
public class PersonController {

	@Autowired
	private PersonService personService;
	
	@PostMapping("/save")
	public ResponseEntity<ResponseApi<PersonDTO>> save(@RequestBody Person person) {
		
		Optional<PersonDTO> optionalPerson =  personService.save(person);
		if (optionalPerson.isPresent()) {
			String message = "Registro almacenado con éxito!";
			return ResponseEntity.status(HttpStatus.OK).body(responseApi(HttpStatus.OK.value(), message, optionalPerson.get()));
		} 
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();		
	}
	
	@GetMapping("/{id}/detalle-basico")
	public ResponseEntity<ResponseApi<PersonDTO>> gerPersonBasicById(@PathVariable Long id) {
		try {
			Optional<PersonDTO> person = personService.gerPersonBasicById(id);
			if (person.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(responseApi(HttpStatus.OK.value(), "", person.get()));
			} 
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping
	public ResponseEntity<List<PersonDTO>> getAllPerons() {		
		try {
			List<PersonDTO> listPerson = personService.getAllPerons();
			return ResponseEntity.status(HttpStatus.OK).body(listPerson);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteById(@PathVariable long id) {
		
		boolean isDelete = personService.deleteById(id);
		if (isDelete) {
			return ResponseEntity.status(HttpStatus.OK).body("El registro con ID " + id + " se ha eliminado");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/{personaId}/publicaciones")
	public ResponseEntity<Person> getPersonIdPublications(@PathVariable Long personaId) {
		try {			
			Optional<Person> personPublication = personService.getPersonsPublications(personaId);
			if (personPublication.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(personPublication.get());	
			}
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/publicaciones")
	public ResponseEntity<List<Person>> getPersonsPublications() {
		try {			
			List<Person> personPublications = personService.getPersonsPublications();
			if (!personPublications.isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK).body(personPublications);	
			}
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	/**
	 * 
	 * @param statusCode
	 * @param message
	 * @param persona
	 * @return
	 */
	private ResponseApi<PersonDTO> responseApi(int statusCode, String message, PersonDTO person) {
		return new ResponseApi(statusCode, message, person);
	}
		
}
