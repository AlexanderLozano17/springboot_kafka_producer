package com.demo.main.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.demo.utils.LogPublication;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicationController {
	
	private final Logger logger = LoggerFactory.getLogger(PublicationController.class);
	
	@Autowired
	private PublicationService publicationService;
	
	@PostMapping("/save")
	public ResponseEntity<ResponseApi<PublicationDTO>> save(@RequestBody Publication publication) {
		logger.info(LogHelper.start(getClass(), "save"));
		
		try {
			Optional<PublicationDTO> publication_ = publicationService.save(publication);
			if (publication_.isPresent()) {
				logger.info(LogHelper.success(getClass(), "save", String.format(LogPublication.PUBLICATION_SAVE_SUCCESS, publication_.get().getId())));
				return ResponseEntity.ok(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.SAVE_SUCCESS, publication_.get()));
			}						
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "save", e.getMessage()), e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseApi(ApiMessages.ERROR, ApiMessages.INTERNAL_SERVER_ERROR, null));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseApi<PublicationDTO>> getPublicationId(@PathVariable Long id) {
		logger.info(LogHelper.start(getClass(), "getPublicationId"));
		
		try {
			Optional<PublicationDTO> publicacion = publicationService.getPublicationId(id);
			if (publicacion.isPresent()) {
				logger.info(LogHelper.success(getClass(), "getPublicationId", String.format(LogPublication.PUBLICATION_FOUND, id)));
				return ResponseEntity.ok(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.SAVE_SUCCESS, publicacion.get()));
			}
			
			logger.warn(LogHelper.warn(getClass(), "getPublicationId", String.format(LogPublication.PUBLICATION_NOT_FOUND, id)));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_NOT_FOUND, null));		
			
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "getPublicationId", e.getMessage()), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseApi(ApiMessages.ERROR, ApiMessages.INTERNAL_SERVER_ERROR, null));
		}
	}
	
	@GetMapping
	public ResponseEntity<ResponseApi<List<PublicationDTO>>> getPublications() {
		logger.info(LogHelper.start(getClass(), "getPublications"));
		
		try {
			List<PublicationDTO> publicacion = publicationService.getPublications();
			if (!publicacion.isEmpty()) {
				logger.info(LogHelper.success(getClass(), "getPublications", String.format(LogPublication.PUBLICATION_LIST_SUCCESS, publicacion.size())));
				return ResponseEntity.ok(new ResponseApi(ApiMessages.SUCCESS, null, null));
			}
			
			logger.warn(LogHelper.warn(getClass(), "getPublications", LogPublication.PUBLICATION_NOT_FOUND));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_NOT_FOUND, null));			
			
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "getPublications", e.getMessage()), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseApi(ApiMessages.ERROR, ApiMessages.INTERNAL_SERVER_ERROR, null));	
		}
	}	
	
	@GetMapping("/persona/{publicacionId}")
	public ResponseEntity<ResponseApi<Publication>> getPublicationPerson(@PathVariable Long publicationId) {
		logger.info(LogHelper.start(getClass(), "getPublicationPerson"));
		
		try {
			Optional<Publication> publicacion = publicationService.getPublicationPerson(publicationId);
			if (publicacion.isPresent()) {
				logger.info(LogHelper.success(getClass(), "getPublicationPerson", String.format(LogPublication.PUBLICATION_FOUND, publicationId)));
				return ResponseEntity.ok(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_FOUND, publicacion.get()));
			}
			
			logger.warn(LogHelper.warn(getClass(), "getPublicationPerson", String.format(LogPublication.PUBLICATION_NOT_FOUND, publicationId)));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_NOT_FOUND, null));			
			
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "getPublicationPerson", e.getMessage()), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseApi(ApiMessages.ERROR, ApiMessages.INTERNAL_SERVER_ERROR, null));	
		}
	}

}
