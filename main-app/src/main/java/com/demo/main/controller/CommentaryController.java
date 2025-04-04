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

@RestController
@RequestMapping("/api/comentario")
public class CommentaryController {
	
	private final Logger logger = LoggerFactory.getLogger(CommentaryController.class);
	
	private CommentaryService CommentaryService;
	
	public CommentaryController(CommentaryService commentaryService) {
		this.CommentaryService = CommentaryService;
	}
	
	@PostMapping("/save")
	public ResponseEntity<ResponseApi<CommentaryDTO>> save(@RequestBody Commentary Commentary) {		
		logger.info(LogHelper.start(getClass(), "save"));
		
		try {
			Optional<CommentaryDTO> Commentary_ = CommentaryService.save(Commentary);		
			if (Commentary_.isPresent()) {
				logger.info(LogHelper.success(getClass(), "save", LogCommentary.COMMENT_SAVE_SUCCESS));
				return ResponseEntity.ok(responseApi(ApiMessages.SUCCESS, ApiMessages.SAVE_SUCCESS, Commentary_.get()));
			}
			
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "save", e.getMessage()), e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseApi(ApiMessages.ERROR, ApiMessages.INTERNAL_SERVER_ERROR, null));
	}
	
	@GetMapping
	public ResponseEntity<ResponseApi<List<Commentary>>> getCommentaries() {
		logger.info(LogHelper.start(getClass(), "getComentaries"));
		
		try {
			List<Commentary> comentaries = CommentaryService.getCommentaries();
			if (!comentaries.isEmpty()) {
				logger.info(LogHelper.success(getClass(), "getComentaries", String.format(LogCommentary.COMMENT_LIST_SUCCESS, comentaries.size())));
				return ResponseEntity.ok(responseApi(ApiMessages.SUCCESS, ApiMessages.LIST_SUCCESS, comentaries));
			} 
			
			logger.warn(LogHelper.warn(getClass(), "getComentaries", String.format(LogCommentary.COMMENT_NOT_FOUND, 0)));
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_NOT_FOUND, null));
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "getCommentaries", e.getMessage()), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseApi(ApiMessages.ERROR, ApiMessages.INTERNAL_SERVER_ERROR, null));
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseApi<CommentaryDTO>> getCommentaryById(Long id) {
		logger.info(LogHelper.start(getClass(), "getCommentaryById"));
		
		try {
			Optional<CommentaryDTO> comentario = CommentaryService.getCommentaryById(id);
			if (comentario.isPresent()) {
				logger.info(LogHelper.success(getClass(), "getCommentaryById", String.format(LogCommentary.COMMENT_FOUND, id)));
				return ResponseEntity.status(HttpStatus.OK).body(responseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_FOUND, comentario.get()));
			}
			
			logger.warn(LogHelper.warn(getClass(), "getCommentaryById", String.format(LogCommentary.COMMENT_NOT_FOUND, id)));
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_NOT_FOUND, null));
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "getCommentaryById", e.getMessage()), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseApi(ApiMessages.ERROR, ApiMessages.INTERNAL_SERVER_ERROR, null));
		}	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseApi<String>> deleteById( @PathVariable Long id) {
		logger.info(LogHelper.start(getClass(), "deleteById"));
		
		boolean esEliminado = CommentaryService.deleteById(id);
		if (esEliminado) {
			logger.info(LogHelper.success(getClass(), "deleteById", String.format(LogCommentary.COMMENT_DELETE_SUCCESS, id)));
			return ResponseEntity.ok(responseApi(ApiMessages.SUCCESS, String.format(ApiMessages.DELETE_SUCCESS, id), null));
		}
		logger.warn(LogHelper.warn(getClass(), "deleteById", String.format(LogCommentary.COMMENT_NOT_FOUND, id)));
		return ResponseEntity.ok(responseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_NOT_FOUND, null));

	}
	
	/**
	 * 
	 * @param statusCode
	 * @param message
	 * @param persona
	 * @return
	 */
	private <T> ResponseApi<T> responseApi(String status, String message, T dataObject) {
		logger.info(LogHelper.start(getClass(), "responseApi"));
		return new ResponseApi(status, message, dataObject);
	}
}
