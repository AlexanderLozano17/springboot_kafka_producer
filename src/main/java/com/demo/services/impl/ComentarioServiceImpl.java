package com.demo.services.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Comentario;
import com.demo.entities.Persona;
import com.demo.repositories.ComentarioRepository;
import com.demo.repositories.PersonaRepository;
import com.demo.services.ComentarioService;

@Service
public class ComentarioServiceImpl implements ComentarioService {
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	@Autowired
	private PersonaRepository personaRepository;
	
	@Override
	public Optional<Comentario> guardar(Comentario comentario) {
		// TODO Auto-generated method stub
		try {
			return Optional.of(comentarioRepository.save(comentario));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return Optional.empty();
		}		
	}

	@Override
	public Optional<Comentario> obtenerId(Long id) {
		// TODO Auto-generated method stub
		return comentarioRepository.findById(id);
	}

	@Override
	public List<Comentario> obtenerTodo() {
		// TODO Auto-generated method stub
		return comentarioRepository.findAll();
	}
	
	public List<Comentario> obtenerComentariosPorPersona(Long personaId) {
		Optional<Persona> persona = personaRepository.findById(personaId);
		return persona.map(comentarioRepository::findByPersona).orElse(Collections.emptyList());
	}

}
