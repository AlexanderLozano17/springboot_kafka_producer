package com.demo.core.services;

import java.util.List;
import java.util.Optional;

import com.demo.core.entities.Publication;
import com.demo.dto.dto.PublicationDTO;

public interface PublicationService {
	
	Optional<Publication> save(Publication publicacion);
	
	Optional<PublicationDTO> getPublicationId(Long id);
	
	List<PublicationDTO> getPublications();
	
	Optional<Publication> getPublicationPerson(Long id);

}
