package com.demo.core.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.core.entities.Comentario;
import com.demo.core.repositories.ComentarioRepository;
import com.demo.core.services.ComentarioService;

@Service
public class ComentarioServiceImpl implements ComentarioService {
	
	@Autowired
	private ComentarioRepository comentarioRepository;
		
	@Override
	public Optional<Comentario> guardar(Comentario comentario) {
		// TODO Auto-generated method stub
		try {
			return Optional.of(comentarioRepository.save(comentario));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return Optional.empty();
		}		
	}

	@Override
	public Optional<Comentario> obtenerId(Long id) {
		// TODO Auto-generated method stub
		return comentarioRepository.findById(id);
	}

	@Override
	public List<Comentario> obtener() {
		// TODO Auto-generated method stub
		return comentarioRepository.findAll();
	}

	@Override
	public boolean eliminar(Long id) {
		// TODO Auto-generated method stub
		try {
			if (!comentarioRepository.existsById(id)) return false;
			comentarioRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
}
