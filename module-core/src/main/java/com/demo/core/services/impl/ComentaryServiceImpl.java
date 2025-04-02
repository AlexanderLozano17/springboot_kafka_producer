package com.demo.core.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.demo.core.entities.Comentary;
import com.demo.core.repositories.ComentaryRepository;
import com.demo.core.services.ComentaryService;

@Service
public class ComentaryServiceImpl implements ComentaryService {
	
	private final Logger logger = LoggerFactory.getLogger(ComentaryServiceImpl.class);
	
	private final ComentaryRepository comentaryRepository;
	
	public ComentaryServiceImpl(ComentaryRepository comentaryRepository) {
		this.comentaryRepository = comentaryRepository;
	}
		
	@Override
	public Optional<Comentary> save(Comentary comentary) {
		// TODO Auto-generated method stub
		try {
			return Optional.of(comentaryRepository.save(comentary));
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("❌ Error al guardar el comentario: {}", e.getMessage(), e);
			return Optional.empty();
		}		
	}

	@Override
	public Optional<Comentary> getComentaryById(Long id) {
		// TODO Auto-generated method stub
		return comentaryRepository.findById(id);
	}

	@Override
	public List<Comentary> getComentaries() {
		// TODO Auto-generated method stub
		return comentaryRepository.findAll();
	}

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		try {
			if (!comentaryRepository.existsById(id)) return false;
			comentaryRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error al eliminar el registro con ID {}", id, e.getMessage(), e);
			return false;
		}
	}
}
