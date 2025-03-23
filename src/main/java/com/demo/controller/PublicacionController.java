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

import com.demo.dto.PublicacionDTO;
import com.demo.entities.Persona;
import com.demo.entities.Publicacion;
import com.demo.services.PublicacionService;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionController {
	
	@Autowired
	private PublicacionService publicacionService;
	
	@PostMapping("/save")
	public ResponseEntity<Publicacion> guardar(@RequestBody Publicacion publicacion) {
		
		Optional<Publicacion> publicacion_ =  publicacionService.guardar(publicacion);
		if (publicacion_.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(publicacion_.get());
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PublicacionDTO> obtenerPublicacionId(@PathVariable Long id) {
		try {
			Optional<PublicacionDTO> publicacion = publicacionService.obtenerPublicacionId(id);
			if (publicacion.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(publicacion.get());
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping
	public ResponseEntity<List<PublicacionDTO>> obtenerPublicaciones() {
		try {
			List<PublicacionDTO> publicacion = publicacionService.obtenerPublicaciones();
			if (!publicacion.isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK).body(publicacion);
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}	
}
