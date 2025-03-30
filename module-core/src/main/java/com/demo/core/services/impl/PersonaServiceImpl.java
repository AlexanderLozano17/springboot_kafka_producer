package com.demo.core.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.core.dto.PersonaDTO;
import com.demo.core.entities.Persona;
import com.demo.core.repositories.PersonaRepository;
import com.demo.core.services.PersonaService;
import com.demo.producer.services.KafkaProducerService;

@Service
public class PersonaServiceImpl implements PersonaService {
	
	private final Logger logger = LoggerFactory.getLogger(PersonaServiceImpl.class);

	private final PersonaRepository personaRepository;
	
	private final KafkaProducerService producerService;
	
	private static final String TOPIC_TEST = "topic-test";
	
	public PersonaServiceImpl(PersonaRepository personaRepository, KafkaProducerService producerService) {
		this.personaRepository = personaRepository;
		this.producerService = producerService;
	}

	@Override
	public Optional<Persona> guardar(Persona persona) {
		// TODO Auto-generated method stub
		try {
//			 Persona savePersona = personaRepository.save(persona);
//			 Optional<Persona> personaOpt = Optional.of(savePersona);
//			 
//			 if (personaOpt.isPresent()) {
				String mensaje = String.format("✅ Persona registrada con éxito (ID: %d, Título: %s)", 1, "alexander lozano");//savePersona.getId(), savePersona.getNombreCompleto());
				producerService.sendMessage(TOPIC_TEST, mensaje); 		 
//			 }	
			 
			 return Optional.of(new Persona()); 	
			
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
	
}
