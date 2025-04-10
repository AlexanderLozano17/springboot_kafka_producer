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
import com.demo.dto.dto.PublicationWithCommentsDTO;
import com.demo.dto.dto.ResponseApi;
import com.demo.main.utils.ApiMessages;
import com.demo.utils.LogHelper;
import com.demo.utils.LogPublication;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/publication")
@Tag(name = "Publicaciones", description = "Operaciones relacionadas con publicaciones")
public class PublicationController {
	
	private final Logger logger = LoggerFactory.getLogger(PublicationController.class);
	
	@Autowired
	private PublicationService publicationService;
	
	@Operation(
		    summary = "Crear publicación",
		    description = "Crea una nueva publicación con los datos proporcionados.",
		    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
		        description = "Objeto de publicación que contiene título, contenido y datos relacionados.",
		        required = true,
		        content = @Content(
		            schema = @Schema(implementation = PublicationDTO.class)
		        )
		    ),
		    responses = {
		        @ApiResponse(responseCode = "201", description = ApiMessages.SAVE_SUCCESS, content = @Content(schema = @Schema(implementation = ResponseApi.class))),
		        @ApiResponse(responseCode = "500", description = ApiMessages.INTERNAL_SERVER_ERROR, content = @Content(schema = @Schema(implementation = ResponseApi.class)))
		    }
		)
	@PostMapping("/create")
	public ResponseEntity<ResponseApi<PublicationDTO>> createPublication(@RequestBody Publication publication) {
		logger.info(LogHelper.start(getClass(), "createPublication"));
		
		try {
			Optional<PublicationDTO> publication_ = publicationService.createPublication(publication);
			if (publication_.isPresent()) {
				logger.info(LogHelper.success(getClass(), "createPublication", String.format(LogPublication.PUBLICATION_SAVE_SUCCESS, publication_.get().getId())));
				logger.info(LogHelper.end(getClass(), "createPublication"));
				return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.SAVE_SUCCESS, publication_.get()));
			}						
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "createPublication", e.getMessage()), e);
		}
		logger.info(LogHelper.end(getClass(), "createPublication"));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseApi(ApiMessages.ERROR, ApiMessages.INTERNAL_SERVER_ERROR, null));
	}
	
	@Operation(
		    summary = "Obtener publicación por ID",
		    description = "Devuelve una publicación específica por su ID.",
		    responses = {
		        @ApiResponse(responseCode = "200", description = ApiMessages.RECORD_FOUND, content = @Content(schema = @Schema(implementation = ResponseApi.class))),
		        @ApiResponse(responseCode = "404", description = ApiMessages.RECORD_NOT_FOUND, content = @Content(schema = @Schema(implementation = ResponseApi.class))),
		        @ApiResponse(responseCode = "500", description = ApiMessages.INTERNAL_SERVER_ERROR, content = @Content(schema = @Schema(implementation = ResponseApi.class)))
		    }
		)
	@GetMapping("/{id}")
	public ResponseEntity<ResponseApi<PublicationDTO>> getPublicationById(@PathVariable Long id) {
		logger.info(LogHelper.start(getClass(), "getPublicationById"));
		
		try {			
			Optional<PublicationDTO> publication = publicationService.getPublicationById(id);
			if (publication.isPresent()) {
				logger.info(LogHelper.success(getClass(), "getPublicationById", String.format(LogPublication.PUBLICATION_FOUND, id)));
				logger.info(LogHelper.end(getClass(), "getPublicationById"));
				return ResponseEntity.ok(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_FOUND, publication.get()));
			}
			
			logger.warn(LogHelper.warn(getClass(), "getPublicationById", String.format(LogPublication.PUBLICATION_NOT_FOUND, id)));
			logger.info(LogHelper.end(getClass(), "getPublicationById"));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_NOT_FOUND, null));
			
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "getPublicationById", e.getMessage()), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseApi(ApiMessages.ERROR, ApiMessages.INTERNAL_SERVER_ERROR, null));
		}
	}
	
	@Operation(
		    summary = "Listar todas las publicaciones",
		    description = "Devuelve una lista de todas las publicaciones registradas.",
		    responses = {
		        @ApiResponse(responseCode = "200", description = ApiMessages.LIST_SUCCESS, content = @Content(schema = @Schema(implementation = ResponseApi.class))),
		        @ApiResponse(responseCode = "404", description = ApiMessages.RECORD_NOT_FOUND, content = @Content(schema = @Schema(implementation = ResponseApi.class))),
		        @ApiResponse(responseCode = "500", description = ApiMessages.INTERNAL_SERVER_ERROR, content = @Content(schema = @Schema(implementation = ResponseApi.class)))
		    }
		)
	@GetMapping
	public ResponseEntity<ResponseApi<List<PublicationDTO>>> getAllPublications() {
		logger.info(LogHelper.start(getClass(), "getAllPublications"));
		
		try {
			List<PublicationDTO> publications = publicationService.getAllPublications();
			if (!publications.isEmpty()) {
				logger.info(LogHelper.success(getClass(), "getAllPublications", String.format(LogPublication.PUBLICATION_LIST_SUCCESS, publications.size())));
				logger.info(LogHelper.end(getClass(), "getAllPublications"));
				return ResponseEntity.ok(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.LIST_SUCCESS, publications));
			}
			
			logger.warn(LogHelper.warn(getClass(), "getAllPublications", LogPublication.PUBLICATION_NOT_FOUND));
			logger.info(LogHelper.end(getClass(), "getAllPublications"));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_NOT_FOUND, null));			
			
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "getAllPublications", e.getMessage()), e);
			logger.info(LogHelper.end(getClass(), "getAllPublications"));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseApi(ApiMessages.ERROR, ApiMessages.INTERNAL_SERVER_ERROR, null));	
		}
	}	
	
	@Operation(
		    summary = "Obtener publicación con comentarios",
		    description = "Devuelve una publicación junto con todos sus comentarios.",
		    responses = {
		        @ApiResponse(responseCode = "200", description = ApiMessages.RECORD_FOUND, content = @Content(schema = @Schema(implementation = ResponseApi.class))), 
		        @ApiResponse(responseCode = "404", description = ApiMessages.RECORD_NOT_FOUND, content = @Content(schema = @Schema(implementation = ResponseApi.class))),
		        @ApiResponse(responseCode = "500", description = ApiMessages.INTERNAL_SERVER_ERROR, content = @Content(schema = @Schema(implementation = ResponseApi.class)))
		    }
		)
	@GetMapping("/{publicationId}/commentaries")
	public ResponseEntity<ResponseApi<PublicationWithCommentsDTO>> getPublicationWithComments(@PathVariable Long publicationId) {
		logger.info(LogHelper.start(getClass(), "getPublicationWithComments"));
		
		try {			
			Optional<PublicationWithCommentsDTO> publicationWithCommentsDTO = publicationService.getPublicationWithComments(publicationId);
			if (publicationWithCommentsDTO.isPresent()) {
				logger.info(LogHelper.success(getClass(), "getPublicationWithComments", String.format(LogPublication.PUBLICATION_FOUND, publicationWithCommentsDTO.get().getId())));
				logger.info(LogHelper.end(getClass(), "getPublicationWithComments"));
				return ResponseEntity.ok(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_FOUND, publicationWithCommentsDTO));				
			}
			
			logger.info(LogHelper.success(getClass(), "getPublicationWithComments", String.format(LogPublication.PUBLICATION_NOT_FOUND, publicationWithCommentsDTO.get().getId())));
			logger.info(LogHelper.end(getClass(), "getPublicationWithComments"));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_NOT_FOUND, publicationWithCommentsDTO));
			
		} catch (Exception e) {
			logger.info(LogHelper.error(getClass(), "getPublicationWithComments", e.getMessage()), e);
			logger.info(LogHelper.end(getClass(), "getPublicationWithComments"));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseApi(ApiMessages.ERROR, ApiMessages.INTERNAL_SERVER_ERROR, null));
		}
		
	}
	
	@Operation(
		    summary = "Eliminar publicación por ID",
		    description = "Elimina una publicación existente a partir de su ID.",
		    responses = {
		        @ApiResponse(responseCode = "200", description = ApiMessages.DELETE_SUCCESS, content = @Content(schema = @Schema(implementation = ResponseApi.class))),
		        @ApiResponse(responseCode = "404", description = ApiMessages.RECORD_NOT_FOUND, content = @Content(schema = @Schema(implementation = ResponseApi.class))),
		        @ApiResponse(responseCode = "500", description = ApiMessages.INTERNAL_SERVER_ERROR, content = @Content(schema = @Schema(implementation = ResponseApi.class)))
		    }
		)
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseApi<String>> deletePublicationById(@PathVariable Long id) {
		logger.info(LogHelper.start(getClass(), "deletePublicationById"));
		
		try {
			if (publicationService.deletePublicationById(id)) {
				logger.info(LogHelper.success(getClass(), "deletePublicationById", String.format(LogPublication.PUBLICATION_DELETE_SUCCESS, id)));
				logger.info(LogHelper.end(getClass(), "deletePublicationById"));
				return ResponseEntity.ok(new ResponseApi(ApiMessages.SUCCESS, String.format(ApiMessages.DELETE_SUCCESS, id), null));
			} else {
				logger.warn(LogHelper.warn(getClass(), "deletePublicationById", String.format(LogPublication.PUBLICATION_NOT_FOUND, id)));
				logger.info(LogHelper.end(getClass(), "deletePublicationById"));
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_NOT_FOUND, null));
			}
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "deletePublicationById", e.getMessage()), e);
			logger.info(LogHelper.end(getClass(), "deletePublicationById"));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseApi(ApiMessages.ERROR, ApiMessages.INTERNAL_SERVER_ERROR, null));	
		}
	}

}
