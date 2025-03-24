package com.demo.core.dto;


import java.io.Serializable;
import java.time.LocalDate;

public class PublicacionDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;	
	private String titulo;
	private String contenido;
	private LocalDate fechaPublicacion;
		
    public PublicacionDTO(Long id, String titulo, String contenido, LocalDate fechaPublicacion) {
        this.id = id;
    	this.titulo = titulo;
        this.contenido = contenido;
        this.fechaPublicacion = fechaPublicacion;
    }

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @return the contenido
	 */
	public String getContenido() {
		return contenido;
	}

	/**
	 * @return the fechaPublicacion
	 */
	public LocalDate getFechaPublicacion() {
		return fechaPublicacion;
	}	
}

