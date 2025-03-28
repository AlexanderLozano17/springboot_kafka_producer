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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.core.entities.Comentario;
import com.demo.core.services.ComentarioService;

@RestController
@RequestMapping("/api/comentario")
public class ComentarioController {
	
	private ComentarioService comentarioService;
	
	@GetMapping
	public ResponseEntity<List<Comentario>> obtener() {
		try {
			List<Comentario> comentarios = comentarioService.obtener();
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
	public ResponseEntity<Comentario> obtenerId(Long id) {
		try {
			Optional<Comentario> comentario = comentarioService.obtenerId(id);
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
	public ResponseEntity<Comentario> guardar(@RequestBody Comentario comentario) {
		Optional<Comentario> comentario_ = comentarioService.guardar(comentario);
		if (comentario_.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(comentario_.get());
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminar( @PathVariable Long id) {
		boolean esEliminado = comentarioService.eliminar(id);
		if (esEliminado) {
			return ResponseEntity.status(HttpStatus.OK).body("El registro con ID " + id + " se ha eliminado");
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}
}
