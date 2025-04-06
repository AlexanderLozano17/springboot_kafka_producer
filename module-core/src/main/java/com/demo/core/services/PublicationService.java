package com.demo.core.services;

import java.util.List;
import java.util.Optional;

import com.demo.core.entities.Publication;
import com.demo.dto.dto.PublicationDTO;

public interface PublicationService {
	
	/**
	 * 
	 * @param publicacion
	 * @return
	 */
	Optional<PublicationDTO> createPublication(Publication publicacion);

	/**
	 * 
	 * @param id
	 * @return
	 */
	Optional<PublicationDTO> getPublicationById(Long id);
	
	/**
	 * 
	 * @return
	 */
	List<PublicationDTO> getAllPublications();
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Optional<Publication> getPublicationWithPersonDetails(Long id);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	boolean deletePublicationById(Long id);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	boolean existPublicationById(Long id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	Optional<PublicationDTO> getPublicationWithComments(Long id);

}
