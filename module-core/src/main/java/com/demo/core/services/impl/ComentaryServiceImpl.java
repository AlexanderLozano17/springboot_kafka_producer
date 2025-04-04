package com.demo.core.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.demo.core.entities.Commentary;
import com.demo.core.repositories.CommentaryRepository;
import com.demo.core.services.CommentaryService;
import com.demo.dto.dto.CommentaryDTO;
import com.demo.dto.dto.ResponseKafka;
import com.demo.producer.services.KafkaProducerPersonService;

@Service
public class ComentaryServiceImpl implements CommentaryService {
	
	private final Logger logger = LoggerFactory.getLogger(ComentaryServiceImpl.class);
	
	private final CommentaryRepository commentaryRepository;
	
	private final KafkaProducerPersonService producerService;
	
	public ComentaryServiceImpl(CommentaryRepository commentaryRepository, KafkaProducerPersonService producerService) {
		this.commentaryRepository = commentaryRepository;
		this.producerService = producerService;
	}
		
	@Override
	public Optional<CommentaryDTO> save(Commentary commentary) {
		// TODO Auto-generated method stub
		try {
			Commentary saveComentary = commentaryRepository.save(commentary);			
			CommentaryDTO CommentaryDTO = CommentaryDTO(saveComentary);
						
			String mensaje = String.format("✅ Comentario registrado con éxito (ID: %d)", saveComentary.getId());

			producerService.sendMessageRecordPerson(new ResponseKafka(mensaje, CommentaryDTO));
			
			return Optional.of(CommentaryDTO);
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("❌ Error al guardar el comentario: {}", e.getMessage(), e);
			return Optional.empty();
		}		
	}

	@Override
	public Optional<CommentaryDTO> getCommentaryById(Long id) {
		// TODO Auto-generated method stub
		return Optional.of(CommentaryDTO(commentaryRepository.findById(id).get()));
	}

	@Override
	public List<Commentary> getCommentaries() {
		// TODO Auto-generated method stub
		return  commentaryRepository.findAll() ;
	}

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		try {
			if (!commentaryRepository.existsById(id)) return false;
			commentaryRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error al eliminar el registro con ID {}", id, e.getMessage(), e);
			return false;
		}
	}
	
	/** 
	 * @param Comentary
	 * @return
	 */
	private CommentaryDTO CommentaryDTO(Commentary comentary) {
		return new CommentaryDTO(comentary.getId(), 
				comentary.getPublication().getId(), 
				comentary.getPerson().getId(), 
				comentary.getDateComentary(), 
				comentary.getContent()); 
	}
	
	/** 
	 * @param Comentary
	 * @return
	 */
	private List<CommentaryDTO> listCommentaryDTO(List<Commentary> listComentary) {
		return listComentary.stream().map(comentary -> CommentaryDTO(comentary)).collect(Collectors.toList());
	}
}
