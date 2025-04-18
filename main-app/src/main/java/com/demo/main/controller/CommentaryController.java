package com.demo.main.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.core.entities.Commentary;
import com.demo.core.services.CommentaryService;
import com.demo.dto.dto.CommentaryDTO;
import com.demo.dto.dto.ResponseApi;
import com.demo.main.utils.ApiMessages;
import com.demo.utils.LogCommentary;
import com.demo.utils.LogHelper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/commentary")
@Tag(name = "Commentary", description = "Operaciones relacionadas con comentarios")
public class CommentaryController {
	
	private final Logger logger = LoggerFactory.getLogger(CommentaryController.class);
	
	private CommentaryService commentaryService;
	
	public CommentaryController(CommentaryService commentaryService) {
		this.commentaryService = commentaryService;
	}
	
	@Operation(
		    summary = "Crear un comentario",
		    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
		        description = "Objeto de comentario que contiene datos relacionados.",
		        required = true,
		        content = @Content(
		            schema = @Schema(implementation = CommentaryDTO.class)
		        )
		    ),
		    responses = {
		        @ApiResponse(responseCode = "201", description = ApiMessages.SAVE_SUCCESS, content = @Content(schema = @Schema(implementation = ResponseApi.class))),
		        @ApiResponse(responseCode = "500", description = ApiMessages.INTERNAL_SERVER_ERROR, content = @Content(schema = @Schema(implementation = ResponseApi.class)))
		    }
		)
	@PostMapping("/create")
	public ResponseEntity<ResponseApi<CommentaryDTO>> createCommentary(@RequestBody Commentary Commentary) {		
		logger.info(LogHelper.start(getClass(), "createCommentary"));
		
		try {
			Optional<CommentaryDTO> Commentary_ = commentaryService.createCommentary(Commentary);		
			if (Commentary_.isPresent()) {
				logger.info(LogHelper.success(getClass(), "createCommentary", String.format(LogCommentary.COMMENT_SAVE_SUCCESS, Commentary_.get().getId())));
				logger.info(LogHelper.end(getClass(), "createCommentary"));
				return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.SAVE_SUCCESS, Commentary_.get()));
			}
			
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "createCommentary", e.getMessage()), e);
		}
		logger.info(LogHelper.end(getClass(), "createCommentary"));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseApi(ApiMessages.ERROR, ApiMessages.INTERNAL_SERVER_ERROR, null));
	}
	
	@Operation(
		    summary = "Obtiene un alista de todos los comentarios",
		    responses = {
		        @ApiResponse(responseCode = "200", description = ApiMessages.RECORD_FOUND, content = @Content(schema = @Schema(implementation = ResponseApi.class))),
		        @ApiResponse(responseCode = "404", description = ApiMessages.RECORD_NOT_FOUND, content = @Content(schema = @Schema(implementation = ResponseApi.class))),
		        @ApiResponse(responseCode = "500", description = ApiMessages.INTERNAL_SERVER_ERROR, content = @Content(schema = @Schema(implementation = ResponseApi.class)))
		    }
		)
	@GetMapping
	public ResponseEntity<ResponseApi<List<Commentary>>> getAllCommentaries() {
		logger.info(LogHelper.start(getClass(), "getAllCommentaries"));
		
		try {
			List<Commentary> commentaries = commentaryService.getAllCommentaries();
			if (!commentaries.isEmpty()) {
				logger.info(LogHelper.success(getClass(), "getAllCommentaries", String.format(LogCommentary.COMMENT_LIST_SUCCESS, commentaries.size())));
				logger.info(LogHelper.end(getClass(), "getAllCommentaries"));
				return ResponseEntity.ok(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.LIST_SUCCESS, commentaries));
			} 
			
			logger.warn(LogHelper.warn(getClass(), "getAllCommentaries", String.format(LogCommentary.COMMENT_NOT_FOUND, 0)));
			logger.info(LogHelper.end(getClass(), "getAllCommentaries"));
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_NOT_FOUND, null));
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "getAllCommentaries", e.getMessage()), e);
			logger.info(LogHelper.end(getClass(), "getAllCommentaries"));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseApi(ApiMessages.ERROR, ApiMessages.INTERNAL_SERVER_ERROR, null));
		}
	}

	@Operation(
		    summary = "Obtiene un comentaio por ID",
		    responses = {
		        @ApiResponse(responseCode = "200", description = ApiMessages.RECORD_FOUND, content = @Content(schema = @Schema(implementation = ResponseApi.class))),
		        @ApiResponse(responseCode = "404", description = ApiMessages.RECORD_NOT_FOUND, content = @Content(schema = @Schema(implementation = ResponseApi.class))),
		        @ApiResponse(responseCode = "500", description = ApiMessages.INTERNAL_SERVER_ERROR, content = @Content(schema = @Schema(implementation = ResponseApi.class)))
		    }
		)
	@GetMapping("/{id}")
	public ResponseEntity<ResponseApi<CommentaryDTO>> getCommentaryById(@PathVariable Long id) {
		logger.info(LogHelper.start(getClass(), "getCommentaryById"));
		
		try {
			Optional<CommentaryDTO> comentario = commentaryService.getCommentaryById(id);
			if (comentario.isPresent()) {
				logger.info(LogHelper.success(getClass(), "getCommentaryById", String.format(LogCommentary.COMMENT_FOUND, id)));
				logger.info(LogHelper.end(getClass(), "getCommentaryById"));
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_FOUND, comentario.get()));
			}
			
			logger.warn(LogHelper.warn(getClass(), "getCommentaryById", String.format(LogCommentary.COMMENT_NOT_FOUND, id)));
			logger.info(LogHelper.end(getClass(), "getCommentaryById"));
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_NOT_FOUND, null));
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "getCommentaryById", e.getMessage()), e);
			logger.info(LogHelper.end(getClass(), "getCommentaryById"));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseApi(ApiMessages.ERROR, ApiMessages.INTERNAL_SERVER_ERROR, null));
		}	
	}
	
	@Operation(
		    summary = "Elimina un comentaio por ID",
		    responses = {
		        @ApiResponse(responseCode = "200", description = ApiMessages.RECORD_FOUND, content = @Content(schema = @Schema(implementation = ResponseApi.class))),
		        @ApiResponse(responseCode = "404", description = ApiMessages.RECORD_NOT_FOUND, content = @Content(schema = @Schema(implementation = ResponseApi.class))),
		        @ApiResponse(responseCode = "500", description = ApiMessages.INTERNAL_SERVER_ERROR, content = @Content(schema = @Schema(implementation = ResponseApi.class)))
		    }
		)
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseApi<String>> deleteCommentaryById( @PathVariable Long id) {
		logger.info(LogHelper.start(getClass(), "deleteCommentaryById"));
		
		boolean esEliminado = commentaryService.deleteCommentaryById(id);
		if (esEliminado) {
			logger.info(LogHelper.success(getClass(), "deleteCommentaryById", String.format(LogCommentary.COMMENT_DELETE_SUCCESS, id)));
			logger.info(LogHelper.end(getClass(), "deleteCommentaryById"));
			return ResponseEntity.ok(new ResponseApi(ApiMessages.SUCCESS, String.format(ApiMessages.DELETE_SUCCESS, id), null));
		}
		logger.warn(LogHelper.warn(getClass(), "deleteCommentaryById", String.format(LogCommentary.COMMENT_NOT_FOUND, id)));
		logger.info(LogHelper.end(getClass(), "deleteCommentaryById"));
		return ResponseEntity.ok(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_NOT_FOUND, null));

	}
}
