package com.demo.core.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.core.dto.PersonaDTO;
import com.demo.core.entities.Persona;
import com.demo.core.repositories.PersonaRepository;
import com.demo.core.services.PersonaService;

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
	@Transactional(readOnly = true)
	public Optional<Persona> obtenerPersonaPublicaciones(Long id) {
		// TODO Auto-generated method stub
		return personaRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PersonaDTO> obtenerPersonas() {
		// TODO Auto-generated method stub
		return personaRepository.obtenerPersonas();
	}

	@Override
	public Optional<PersonaDTO> obtenerPersonaId(Long id) {
		// TODO Auto-generated method stub
		return personaRepository.findPersonaBasicById(id);
	}
	
	@Override
	public boolean eliminarId(Long id) {
		
		if (!personaRepository.existsById(id))  return false;		
		try {
			personaRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
	}
	
}
