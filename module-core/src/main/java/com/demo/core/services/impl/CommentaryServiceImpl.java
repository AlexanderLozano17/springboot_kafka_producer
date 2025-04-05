package com.demo.core.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.core.entities.Commentary;
import com.demo.core.repositories.CommentaryRepository;
import com.demo.core.services.CommentaryService;
import com.demo.dto.dto.CommentaryDTO;
import com.demo.dto.dto.ResponseKafka;
import com.demo.producer.services.KafkaProducerCommentaryService;
import com.demo.producer.services.KafkaProducerPersonService;
import com.demo.utils.LogCommentary;
import com.demo.utils.LogHelper;
import com.demo.utils.LogPublication;

@Service
public class CommentaryServiceImpl implements CommentaryService {
	
	private final Logger logger = LoggerFactory.getLogger(CommentaryServiceImpl.class);
	
	private final CommentaryRepository commentaryRepository;
	
	private final KafkaProducerCommentaryService producerService;
	
	public CommentaryServiceImpl(CommentaryRepository commentaryRepository, KafkaProducerCommentaryService producerService) {
		this.commentaryRepository = commentaryRepository;
		this.producerService = producerService;
	}
		
	@Override
	@Transactional
	public Optional<CommentaryDTO> createCommentary(Commentary commentary) {
		logger.info(LogHelper.start(getClass(), "createCommentary"));
		
		try {
			Commentary saveCommentary = commentaryRepository.save(commentary);	
			String mensaje = String.format(LogPublication.PUBLICATION_SAVE_SUCCESS, saveCommentary.getId());			
			logger.info(LogHelper.success(getClass(), "createCommentary", mensaje));
			
			CommentaryDTO commentaryDTO = getommentaryDTO(saveCommentary);		
			producerService.sendMessageRecordCommentary(new ResponseKafka(mensaje, commentaryDTO));
			
			return Optional.of(commentaryDTO);
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(LogHelper.error(getClass(), "createCommentary", e.getMessage()), e);
			return Optional.empty();
		}		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<CommentaryDTO> getCommentaryById(Long id) {
		logger.info(LogHelper.start(getClass(), "getCommentaryById"));
		Commentary commentary = commentaryRepository.findById(id).get();
		return Optional.of(getommentaryDTO(commentary));
	}

	@Override
	@Transactional(readOnly = true)
	public List<Commentary> getAllCommentaries() {
		logger.info(LogHelper.start(getClass(), "getAllCommentaries"));
		return  commentaryRepository.findAll() ;
	}

	@Override
	@Transactional
	public boolean deleteCommentaryById(Long id) {
		logger.info(LogHelper.start(getClass(), "deleteCommentaryById"));
		try {
			if (!commentaryRepository.existsById(id)) {
				logger.warn(LogHelper.warn(getClass(), "deleteCommentaryById", String.format(LogCommentary.COMMENT_NOT_FOUND, id)));
				return false;
			}
			commentaryRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "deleteCommentaryById", e.getMessage()), e);
			return false;
		}
	}
	
	/** 
	 * @param Commentary
	 * @return
	 */
	private CommentaryDTO getommentaryDTO(Commentary commentary) {
		logger.info(LogHelper.start(getClass(), "getommentaryDTO"));
		return new CommentaryDTO(commentary.getId(), 
				commentary.getPublication().getId(), 
				commentary.getPerson().getId(), 
				commentary.getDateCommentary(), 
				commentary.getContent()); 
	}
	
	/** 
	 * @param Commentary
	 * @return
	 */
	private List<CommentaryDTO> getListCommentaryDTO(List<Commentary> listCommentary) {
		return listCommentary.stream().map(commentary -> getommentaryDTO(commentary)).collect(Collectors.toList());
	}
}
