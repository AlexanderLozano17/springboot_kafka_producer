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

import com.demo.core.entities.Persona;
import com.demo.core.services.PersonaService;
import com.demo.dto.dto.PersonaDTO;
import com.demo.dto.dto.ResponseApi;

@RestController
@RequestMapping("/api/persona")
public class PersonaController {

	@Autowired
	private PersonaService personaService;
	
	@PostMapping("/save")
	public ResponseEntity<ResponseApi<PersonaDTO>> guardar(@RequestBody Persona persona) {
		
		Optional<PersonaDTO> optionalPersona =  personaService.guardar(persona);
		if (optionalPersona.isPresent()) {
			String mensaje = "Registro almacenado con éxito!";
			return ResponseEntity.status(HttpStatus.OK).body(responseApi(HttpStatus.OK.value(), mensaje, optionalPersona.get()));
		} 
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();		
	}
	
	@GetMapping("/{id}/detalle-basico")
	public ResponseEntity<ResponseApi<PersonaDTO>> obtenerPersona(@PathVariable Long id) {
		try {
			Optional<PersonaDTO> persona = personaService.obtenerId(id);
			if (persona.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(responseApi(HttpStatus.OK.value(), "", persona.get()));
			} 
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping
	public ResponseEntity<List<PersonaDTO>> obtenerPersonas() {		
		try {
			List<PersonaDTO> listaPersonas = personaService.obtener();
			return ResponseEntity.status(HttpStatus.OK).body(listaPersonas);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarId(@PathVariable long id) {
		
		boolean esELiminado = personaService.eliminarId(id);
		if (esELiminado) {
			return ResponseEntity.status(HttpStatus.OK).body("El registro con ID " + id + " se ha eliminado");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/{personaId}/publicaciones")
	public ResponseEntity<Persona> obtenerPersonaIdPublicaciones(@PathVariable Long personaId) {
		try {			
			Optional<Persona> personaPublicaciones = personaService.obtenerPersonaIdPublicaciones(personaId);
			if (personaPublicaciones.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(personaPublicaciones.get());	
			}
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/publicaciones")
	public ResponseEntity<List<Persona>> obtenerPersonaPublicaciones() {
		try {			
			List<Persona> personaPublicaciones = personaService.obtenerPersonaPublicaciones();
			if (!personaPublicaciones.isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK).body(personaPublicaciones);	
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
	private ResponseApi<PersonaDTO> responseApi(int statusCode, String message, PersonaDTO persona) {
		return new ResponseApi(statusCode, message, persona);
	}
		
}
