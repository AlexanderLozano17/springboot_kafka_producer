package com.demo.main.controller;

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

import com.demo.core.entities.Publication;
import com.demo.core.services.PublicationService;
import com.demo.dto.dto.PublicationDTO;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicationController {
	
	@Autowired
	private PublicationService publicationService;
	
	@PostMapping("/save")
	public ResponseEntity<Publication> save(@RequestBody Publication publication) {
		
		Optional<Publication> publication_ =  publicationService.save(publication);
		if (publication_.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(publication_.get());
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PublicationDTO> getPublicationId(@PathVariable Long id) {
		try {
			Optional<PublicationDTO> publicacion = publicationService.getPublicationId(id);
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
	public ResponseEntity<List<PublicationDTO>> getPublications() {
		try {
			List<PublicationDTO> publicacion = publicationService.getPublications();
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
	
	@GetMapping("/persona/{publicacionId}")
	public ResponseEntity<Publication> getPublicationPerson(@PathVariable Long publicationId) {
		try {
			Optional<Publication> publicacion = publicationService.getPublicationPerson(publicationId);
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
}
