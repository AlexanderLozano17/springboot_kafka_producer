package com.demo.core.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.core.entities.Publication;
import com.demo.core.repositories.PublicationRepository;
import com.demo.core.services.PublicationService;
import com.demo.dto.dto.PublicationDTO;
import com.demo.dto.dto.ResponseKafka;
import com.demo.producer.services.KafkaProducerPublicationService;
import com.demo.utils.LogHelper;
import com.demo.utils.LogPublication;

@Service
public class PublicationServiceImpl implements PublicationService {
	
	private static final Logger logger = LoggerFactory.getLogger(PublicationServiceImpl.class);
	
	private final PublicationRepository publicationRepository;
	
	private final KafkaProducerPublicationService producerService;

	public PublicationServiceImpl(PublicationRepository publicationRepository, KafkaProducerPublicationService producerService) {
        this.publicationRepository = publicationRepository;
		this.producerService = producerService;
    }
	
	@Override
	public Optional<PublicationDTO> save(Publication publicacion) {
		logger.info(LogHelper.start(getClass(), "save"));
		
	    try {
	        Publication savedPublication = publicationRepository.save(publicacion);	 
	        logger.info(LogHelper.success(getClass(), "save", String.format(LogPublication.PUBLICATION_SAVE_SUCCESS, savedPublication.getId())));
	        
	        PublicationDTO publicationOpt = publicationDTO(savedPublication);
	        
	        String mensaje = String.format(LogPublication.PUBLICATION_SAVE_SUCCESS, savedPublication.getId());
	        
	        producerService.sendMessageRecordPublication(new ResponseKafka(mensaje, publicationOpt));
	   
	        return Optional.of(publicationOpt);
	        
	    } catch (Exception e) {
	    	logger.info(LogHelper.error(getClass(), "save", String.format(LogPublication.PUBLICATION_SAVE_ERROR, e.getMessage())), e);
	        return Optional.empty();
	    }
	}


	@Override
	public Optional<PublicationDTO> getPublicationId(Long id) {
		logger.info(LogHelper.start(getClass(), "getPublicationId"));
		return Optional.of(publicationDTO(publicationRepository.getPublicationId(id).get()));
	}

	@Override
	public List<PublicationDTO> getPublications() {
		logger.info(LogHelper.start(getClass(), "getPublications"));
		return getPublicationDTO(publicationRepository.getPublications());
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Publication> getPublicationPerson(Long id) {
		logger.info(LogHelper.start(getClass(), "getPublicationPerson"));
		
		try {
			return publicationRepository.findById(id);
		} catch (Exception e) {
			logger.info(LogHelper.error(getClass(), "findById", String.format(String.format(LogPublication.PUBLICATION_LIST_ERROR, id), e.getMessage())), e);
			logger.error("Error al obtener las publicacione registro con ID {}", id, e.getMessage(), e);
			return Optional.empty();
		}
	}
	
	/** 
	 * @param Comentary
	 * @return
	 */
	private PublicationDTO publicationDTO(Publication publication) {
		logger.info(LogHelper.start(getClass(), "publicationDTO"));
		
		return new PublicationDTO(publication.getId(), 
				publication.getTitle(), 
				publication.getContent(),
				publication.getDatePublication()); 
	}
	
	/** 
	 * @param listPublication
	 * @return
	 */
	private List<PublicationDTO> getPublicationDTO(List<Publication> listPublication) {
		logger.info(LogHelper.start(getClass(), "getPublicationDTO"));
		return listPublication.stream().map(publication -> publicationDTO(publication)).collect(Collectors.toList()); 
	}
}
