package com.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Publicacion;
import com.demo.repositories.PublicacionRepository;
import com.demo.services.PublicacionService;

@Service
public class PublicacionServiceImpl implements PublicacionService {
	
	@Autowired
	private PublicacionRepository publicacionRepository;

	@Override
	public Optional<Publicacion> guardar(Publicacion publicacion) {
		// TODO Auto-generated method stub
		try {
			return Optional.of(publicacionRepository.save(publicacion));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return Optional.empty();
		}
	}

	@Override
	public Optional<Publicacion> obtenerId(Long id) {
		// TODO Auto-generated method stub
		return publicacionRepository.findById(id);
	}

	@Override
	public List<Publicacion> obtenerTodo() {
		// TODO Auto-generated method stub
		return publicacionRepository.findAll();
	}

}
