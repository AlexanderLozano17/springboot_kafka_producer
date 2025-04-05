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
import com.demo.utils.LogPerson;
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
	@Transactional
	public Optional<PublicationDTO> createPublication(Publication publicacion) {
		logger.info(LogHelper.start(getClass(), "createPublication"));
		
	    try {
	        Publication savedPublication = publicationRepository.save(publicacion);	 	        
	        String mensaje = String.format(LogPublication.PUBLICATION_SAVE_SUCCESS, savedPublication.getId());
	        logger.info(LogHelper.success(getClass(), "createPublication", mensaje));
	        
	        PublicationDTO publicationOpt = publicationDTO(savedPublication);        
	        producerService.sendMessageRecordPublication(new ResponseKafka(mensaje, publicationOpt));
	   
	        return Optional.of(publicationOpt);
	        
	    } catch (Exception e) {
	    	logger.info(LogHelper.error(getClass(), "createPublication", String.format(LogPublication.PUBLICATION_SAVE_ERROR, e.getMessage())), e);
	        return Optional.empty();
	    }
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<PublicationDTO> getPublicationById(Long id) {
		logger.info(LogHelper.start(getClass(), "getPublicationById"));		
		return Optional.of(publicationRepository.getPublicationById(id).get());
	}

	@Override
	@Transactional(readOnly = true)
	public List<PublicationDTO> getAllPublications() {
		logger.info(LogHelper.start(getClass(), "getAllPublications"));
		return publicationRepository.getPublications();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Publication> getPublicationWithPersonDetails(Long id) {
		logger.info(LogHelper.start(getClass(), "getPublicationWithPersonDetails"));
		
		try {
			return publicationRepository.findById(id);
		} catch (Exception e) {
			logger.info(LogHelper.error(getClass(), "findById", String.format(String.format(LogPublication.PUBLICATION_LIST_ERROR, id), e.getMessage())), e);
			logger.error("Error al obtener las publicacione registro con ID {}", id, e.getMessage(), e);
			return Optional.empty();
		}
	}

	@Override
	@Transactional
	public boolean deletePublicationById(Long id) {
		logger.info(LogHelper.start(getClass(), "deletePublicationById"));
				
		if (!publicationRepository.existsById(id)) {
			logger.warn(LogHelper.warn(getClass(), "deletePublicationById", String.format(LogPublication.PUBLICATION_NOT_FOUND, id)));
			return false;
		}
		
		publicationRepository.deleteById(id);
		logger.info(LogHelper.success(getClass(), "deletePublicationById", String.format(LogPublication.PUBLICATION_DELETE_SUCCESS, id)));
		return true;	
	}
	
	@Override
	@Transactional(readOnly = true)
	public boolean existPublicationById(Long id) {	
		logger.info(LogHelper.start(getClass(), "existPublicationById"));
		return publicationRepository.existsById(id);
	}
	
	/** 
	 * @param Commentary
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
