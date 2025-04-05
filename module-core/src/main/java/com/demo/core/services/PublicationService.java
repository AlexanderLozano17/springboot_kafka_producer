package com.demo.core.services;

import java.util.List;
import java.util.Optional;

import com.demo.core.entities.Publication;
import com.demo.dto.dto.PublicationDTO;

public interface PublicationService {
	
	Optional<PublicationDTO> createPublication(Publication publicacion);
	
	Optional<PublicationDTO> getPublicationById(Long id);
	
	List<PublicationDTO> getAllPublications();
	
	Optional<Publication> getPublicationWithPersonDetails(Long id);
	
	boolean deletePublicationById(Long id);
	
	boolean existPublicationById(Long id);

}
