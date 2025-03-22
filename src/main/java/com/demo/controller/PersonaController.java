package com.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entities.Persona;
import com.demo.services.PersonaService;

@RestController
@RequestMapping("/api/persona")
public class PersonaController {

	@Autowired
	private PersonaService personaService;
	
	@PostMapping("/save")
	public ResponseEntity<Persona> guardar(@RequestBody Persona persona) {
		
		Optional<Persona> optionalPersona =  personaService.guardar(persona);
		if (optionalPersona.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(optionalPersona.get());
		} 
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();		
	}
	
	@GetMapping("/{id}/publicaciones")
	public ResponseEntity<Persona> obtenerPersonaPublicaciones(@PathVariable Long id) {
		
		try {
			Optional<Persona> persona = personaService.obtenerPersonaPublicaciones(id);
			if (persona.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(persona.get());
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/{id}/detalle-basico")
	public ResponseEntity<Persona> obtenerPersona(@PathVariable Long id) {
		try {
			Optional<Persona> persona = personaService.obtenerPersona(id);
			if (persona.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(persona.get());
			} 
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Persona>> obtenerPersonas() {		
		try {
			List<Persona> listaPersonas = personaService.obtenerTodo();
			return ResponseEntity.status(HttpStatus.OK).body(listaPersonas);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
}
