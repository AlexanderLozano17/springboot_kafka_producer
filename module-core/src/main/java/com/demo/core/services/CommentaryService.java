package com.demo.core.services;

import java.util.List;
import java.util.Optional;

import com.demo.core.entities.Commentary;
import com.demo.dto.dto.CommentaryDTO;

public interface CommentaryService {

	Optional<CommentaryDTO> createCommentary(Commentary commentary);
	
	Optional<CommentaryDTO> getCommentaryById(Long id);
	
	List<Commentary> getAllCommentaries();
	
	boolean deleteCommentaryById(Long id);
	
}
