package com.demo.core.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.core.entities.Publication;
import com.demo.core.repositories.PublicationRepository;
import com.demo.core.services.PublicationService;
import com.demo.dto.dto.PublicationDTO;
import com.demo.producer.services.KafkaProducerPersonService;

@Service
public class PublicationServiceImpl implements PublicationService {
	
	private static final Logger logger = LoggerFactory.getLogger(PublicationServiceImpl.class);
	
	private final PublicationRepository publicationRepository;
	
	private final KafkaProducerPersonService producerService;

	public PublicationServiceImpl(PublicationRepository publicationRepository, KafkaProducerPersonService producerService) {
        this.publicationRepository = publicationRepository;
		this.producerService = producerService;
    }
	
	@Override
	public Optional<Publication> save(Publication publicacion) {
	    try {
	        Publication savedPublication = publicationRepository.save(publicacion);
	        Optional<Publication> publicationOpt = Optional.of(savedPublication);
	        
	        if (publicationOpt.isPresent()) {
	            String mensaje = String.format("✅ Publicación registrada con éxito (ID: %d, Título: %s)", savedPublication.getId(), savedPublication.getTitle());
	            //producerService.sendMessage(mensaje);
	        }

	        return publicationOpt;
	        
	    } catch (Exception e) {
	        logger.error("❌ Error al guardar la publicación: {}", e.getMessage(), e);
	        return Optional.empty();
	    }
	}


	@Override
	public Optional<PublicationDTO> getPublicationId(Long id) {
		// TODO Auto-generated method stub
		return publicationRepository.getPublicationId(id);
	}

	@Override
	public List<PublicationDTO> getPublications() {
		// TODO Auto-generated method stub
		return publicationRepository.getPublications();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Publication> getPublicationPerson(Long id) {
		// TODO Auto-generated method stub		
		try {
			return publicationRepository.findById(id);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error al obtener las publicacione registro con ID {}", id, e.getMessage(), e);
			return Optional.empty();
		}
	}
	
}
