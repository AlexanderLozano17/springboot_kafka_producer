package com.demo.main.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.core.entities.Comentary;
import com.demo.core.services.ComentaryService;

@RestController
@RequestMapping("/api/comentario")
public class ComentaryController {
	
	private ComentaryService comentaryService;
	
	@GetMapping
	public ResponseEntity<List<Comentary>> getComentaries() {
		try {
			List<Comentary> comentarios = comentaryService.getComentaries();
			if (!comentarios.isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK).body(comentarios);
			} 
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Comentary> getComentaryById(Long id) {
		try {
			Optional<Comentary> comentario = comentaryService.getComentaryById(id);
			if (comentario.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(comentario.get());
			}
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}	
	}
	
	@PostMapping("/save")
	public ResponseEntity<Comentary> save(@RequestBody Comentary comentario) {
		Optional<Comentary> comentario_ = comentaryService.save(comentario);
		if (comentario_.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(comentario_.get());
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteById( @PathVariable Long id) {
		boolean esEliminado = comentaryService.deleteById(id);
		if (esEliminado) {
			return ResponseEntity.status(HttpStatus.OK).body("El registro con ID " + id + " se ha eliminado");
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}
}
