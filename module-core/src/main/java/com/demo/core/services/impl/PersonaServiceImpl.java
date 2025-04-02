package com.demo.core.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.core.entities.Persona;
import com.demo.core.repositories.PersonaRepository;
import com.demo.core.services.PersonaService;
import com.demo.dto.dto.PersonaDTO;
import com.demo.dto.dto.ResponseKafka;
import com.demo.producer.services.KafkaProducerPersonaService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PersonaServiceImpl implements PersonaService {
	
	private final Logger logger = LoggerFactory.getLogger(PersonaServiceImpl.class);

	private final PersonaRepository personaRepository;
	
	private final KafkaProducerPersonaService producerService;
		
	public PersonaServiceImpl(PersonaRepository personaRepository, KafkaProducerPersonaService producerService) {
		this.personaRepository = personaRepository;
		this.producerService = producerService;
	}
	
	@Override
	public Optional<PersonaDTO> guardar(Persona persona) {
		// TODO Auto-generated method stub
		try {
			Persona savePersona = personaRepository.save(persona);	
			PersonaDTO personaDTO = personaDTO(savePersona);
			
			String mensaje = String.format("✅ Persona registrada con éxito (ID: %d, Título: %s)", savePersona.getId(), savePersona.getNombreCompleto());

			producerService.sendMessagePersona(new ResponseKafka(mensaje, personaDTO)); 		 
			 
			return Optional.of(personaDTO); 	
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("❌ Error al guardar la persona: {}", e.getMessage(), e);
			return Optional.empty();
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Persona> obtenerPersonaIdPublicaciones(Long id) {
		// TODO Auto-generated method stub
		return personaRepository.findById(id);
	}
	
	@Transactional(readOnly = true)
	public List<Persona> obtenerPersonaPublicaciones() {
		// TODO Auto-generated method stub	
		return personaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<PersonaDTO> obtener() {
		// TODO Auto-generated method stub
		return personaRepository.obtenerPersonas();
	}

	@Override
	public Optional<PersonaDTO> obtenerId(Long id) {
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
	
	/**
	 * 
	 * @param persona
	 * @return
	 */
	private PersonaDTO personaDTO(Persona persona) {
		return new PersonaDTO(persona.getId(),
				persona.getNombres(), 
				persona.getApellidos(),
				persona.getEdad(), 
				persona.getEmail(), 
				persona.getTelefono()); 
	}
	
}
