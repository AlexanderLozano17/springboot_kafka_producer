package com.demo.core.entities;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comentario")
public class Comentario implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "publicacion_id") // Define la clave foranea
	private Publicacion publicacion;
	
	@ManyToOne
	@JoinColumn(name = "persona_id") // Define la clave foranea
	private Persona persona;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate fechaComentario;
	
	public Comentario() {}
	
    public Comentario(Publicacion publicacion, Persona persona) {
        this.publicacion = publicacion;
        this.persona = persona;
        this.fechaComentario = LocalDate.now(); // Fecha por defecto al crear
    }

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the publicacion
	 */
	public Publicacion getPublicacion() {
		return publicacion;
	}

	/**
	 * @param publicacion the publicacion to set
	 */
	public void setPublicacion(Publicacion publicacion) {
		this.publicacion = publicacion;
	}

	/**
	 * @return the fechaCimentario
	 */
	public LocalDate getFechaComentario() {
		return fechaComentario;
	}

	/**
	 * @param fechaCimentario the fechaCimentario to set
	 */
	public void setFechaComentario(LocalDate fechaComentario) {
		this.fechaComentario = fechaComentario;
	}

	/**
	 * @return the persona
	 */
	public Persona getPersona() {
		return persona;
	}

	/**
	 * @param persona the persona to set
	 */
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
}
