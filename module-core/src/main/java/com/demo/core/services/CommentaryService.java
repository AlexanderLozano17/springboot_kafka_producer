package com.demo.core.services;

import java.util.List;
import java.util.Optional;

import com.demo.core.entities.Commentary;
import com.demo.dto.dto.CommentaryDTO;

public interface CommentaryService {

	/**
	 * 
	 * @param commentary
	 * @return
	 */
	Optional<CommentaryDTO> createCommentary(Commentary commentary);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Optional<CommentaryDTO> getCommentaryById(Long id);
	
	/**
	 * 
	 * @return
	 */
	List<Commentary> getAllCommentaries();
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	boolean deleteCommentaryById(Long id);
	
}
