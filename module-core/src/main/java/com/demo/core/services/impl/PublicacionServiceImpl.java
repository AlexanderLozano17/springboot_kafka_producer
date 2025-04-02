package com.demo.core.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.core.entities.Publicacion;
import com.demo.core.repositories.PublicacionRepository;
import com.demo.core.services.PublicacionService;
import com.demo.dto.dto.PublicacionDTO;
import com.demo.producer.services.KafkaProducerPersonaService;

@Service
public class PublicacionServiceImpl implements PublicacionService {
	
	private static final Logger logger = LoggerFactory.getLogger(PublicacionServiceImpl.class);
	
	private final PublicacionRepository publicacionRepository;
	
	private final KafkaProducerPersonaService producerService;

	public PublicacionServiceImpl(PublicacionRepository publicacionRepository, KafkaProducerPersonaService producerService) {
        this.publicacionRepository = publicacionRepository;
		this.producerService = producerService;
    }
	
	@Override
	public Optional<Publicacion> guardar(Publicacion publicacion) {
	    try {
	        Publicacion savedPublicacion = publicacionRepository.save(publicacion);
	        Optional<Publicacion> publicacionOpt = Optional.of(savedPublicacion);
	        
	        if (publicacionOpt.isPresent()) {
	            String mensaje = String.format("✅ Publicación registrada con éxito (ID: %d, Título: %s)", savedPublicacion.getId(), savedPublicacion.getTitulo());
	            //producerService.sendMessage(mensaje);
	        }

	        return publicacionOpt;
	        
	    } catch (Exception e) {
	        logger.error("❌ Error al guardar la publicación: {}", e.getMessage(), e);
	        return Optional.empty();
	    }
	}


	@Override
	public Optional<PublicacionDTO> obtenerPublicacionId(Long id) {
		// TODO Auto-generated method stub
		return publicacionRepository.obtenerPublicacionId(id);
	}

	@Override
	public List<PublicacionDTO> obtenerPublicaciones() {
		// TODO Auto-generated method stub
		return publicacionRepository.obtenerPublicaciones();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Publicacion> obtenerPublicacionPersona(Long id) {
		// TODO Auto-generated method stub		
		try {
			return publicacionRepository.findById(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return Optional.empty();
		}
	}
	
}
