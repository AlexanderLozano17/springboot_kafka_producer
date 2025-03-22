package com.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Persona;
import com.demo.repositories.PersonaRepository;
import com.demo.services.PersonaService;

@Service
public class PersonaServiceImpl implements PersonaService {

	@Autowired
	private PersonaRepository personaRepository;

	@Override
	public Optional<Persona> guardar(Persona persona) {
		// TODO Auto-generated method stub
		try {
			return Optional.of(personaRepository.save(persona));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return Optional.empty();
		}
	}

	@Override
	public Optional<Persona> obtenerPersonaPublicaciones(Long id) {
		// TODO Auto-generated method stub
		return personaRepository.findById(id);
	}

	@Override
	public List<Persona> obtenerTodo() {
		// TODO Auto-generated method stub
		return personaRepository.findAll();
	}

	@Override
	public Optional<Persona> obtenerPersona(Long id) {
		// TODO Auto-generated method stub
		return personaRepository.findPersonaBasicById(id);
	}
	
}
