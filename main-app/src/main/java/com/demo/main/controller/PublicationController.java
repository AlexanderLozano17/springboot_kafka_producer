package com.demo.main.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.core.entities.Publication;
import com.demo.core.services.PublicationService;
import com.demo.dto.dto.PublicationDTO;
import com.demo.dto.dto.ResponseApi;
import com.demo.main.utils.ApiMessages;
import com.demo.utils.LogHelper;
import com.demo.utils.LogPerson;
import com.demo.utils.LogPublication;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicationController {
	
	private final Logger logger = LoggerFactory.getLogger(PublicationController.class);
	
	@Autowired
	private PublicationService publicationService;
	
	@PostMapping("/create")
	public ResponseEntity<ResponseApi<PublicationDTO>> createPublication(@RequestBody Publication publication) {
		logger.info(LogHelper.start(getClass(), "createPublication"));
		
		try {
			Optional<PublicationDTO> publication_ = publicationService.createPublication(publication);
			if (publication_.isPresent()) {
				logger.info(LogHelper.success(getClass(), "createPublication", String.format(LogPublication.PUBLICATION_SAVE_SUCCESS, publication_.get().getId())));
				return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.SAVE_SUCCESS, publication_.get()));
			}						
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "createPublication", e.getMessage()), e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseApi(ApiMessages.ERROR, ApiMessages.INTERNAL_SERVER_ERROR, null));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseApi<PublicationDTO>> getPublicationById(@PathVariable Long id) {
		logger.info(LogHelper.start(getClass(), "getPublicationById"));
		
		try {			
			Optional<PublicationDTO> publication = publicationService.getPublicationById(id);
			if (publication.isPresent()) {
				logger.info(LogHelper.success(getClass(), "getPublicationById", String.format(LogPublication.PUBLICATION_FOUND, id)));
				return ResponseEntity.ok(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_FOUND, publication.get()));
			}
			
			logger.warn(LogHelper.warn(getClass(), "getPublicationById", String.format(LogPublication.PUBLICATION_NOT_FOUND, id)));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_NOT_FOUND, null));
			
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "getPublicationById", e.getMessage()), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseApi(ApiMessages.ERROR, ApiMessages.INTERNAL_SERVER_ERROR, null));
		}
	}
	
	@GetMapping
	public ResponseEntity<ResponseApi<List<PublicationDTO>>> getAllPublications() {
		logger.info(LogHelper.start(getClass(), "getAllPublications"));
		
		try {
			List<PublicationDTO> publication = publicationService.getAllPublications();
			if (!publication.isEmpty()) {
				logger.info(LogHelper.success(getClass(), "getAllPublications", String.format(LogPublication.PUBLICATION_LIST_SUCCESS, publication.size())));
				return ResponseEntity.ok(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.LIST_SUCCESS, publication));
			}
			
			logger.warn(LogHelper.warn(getClass(), "getAllPublications", LogPublication.PUBLICATION_NOT_FOUND));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_NOT_FOUND, null));			
			
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "getAllPublications", e.getMessage()), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseApi(ApiMessages.ERROR, ApiMessages.INTERNAL_SERVER_ERROR, null));	
		}
	}	
	
	@GetMapping("/persona/{publicacionId}")
	public ResponseEntity<ResponseApi<Publication>> getPublicationWithPersonDetails(@PathVariable Long publicationId) {
		logger.info(LogHelper.start(getClass(), "getPublicationWithPersonDetails"));
		
		try {
			Optional<Publication> publication = publicationService.getPublicationWithPersonDetails(publicationId);
			if (publication.isPresent()) {
				logger.info(LogHelper.success(getClass(), "getPublicationWithPersonDetails", String.format(LogPublication.PUBLICATION_FOUND, publicationId)));
				return ResponseEntity.ok(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_FOUND, publication.get()));
			}
			
			logger.warn(LogHelper.warn(getClass(), "getPublicationWithPersonDetails", String.format(LogPublication.PUBLICATION_NOT_FOUND, publicationId)));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_NOT_FOUND, null));			
			
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "getPublicationWithPersonDetails", e.getMessage()), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseApi(ApiMessages.ERROR, ApiMessages.INTERNAL_SERVER_ERROR, null));	
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseApi<String>> deletePublicationById(@PathVariable Long id) {
		logger.info(LogHelper.start(getClass(), "deletePublicationById"));
		
		try {
			if (publicationService.deletePublicationById(id)) {
				logger.info(LogHelper.success(getClass(), "deletePublicationById", String.format(LogPublication.PUBLICATION_DELETE_SUCCESS, id)));
				return ResponseEntity.ok(new ResponseApi(ApiMessages.SUCCESS, String.format(ApiMessages.DELETE_SUCCESS, id), null));
			} else {
				logger.warn(LogHelper.warn(getClass(), "deletePublicationById", String.format(LogPublication.PUBLICATION_NOT_FOUND, id)));
				return ResponseEntity.ok(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_NOT_FOUND, null));
			}
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "deletePublicationById", e.getMessage()), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseApi(ApiMessages.ERROR, ApiMessages.INTERNAL_SERVER_ERROR, null));	
		}
	}

}
