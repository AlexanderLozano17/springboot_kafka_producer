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
@Table(name = "comentary")
public class Comentary implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "publication_id") // Define la clave foranea
	private Publication publication;
	
	@ManyToOne
	@JoinColumn(name = "person_id") // Define la clave foranea
	private Person person;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dateComentary;
	
	private String comentary;
	
	public Comentary() {}
	
    public Comentary(Publication publication, Person person, String comentary) {
        this.publication = publication;
        this.person = person;
        this.comentary = comentary;
        this.dateComentary = LocalDate.now(); // Fecha por defecto al crear
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
	public Publication getPublication() {
		return publication;
	}

	/**
	 * @param publicacion the publicacion to set
	 */
	public void setPublication(Publication publication) {
		this.publication = publication;
	}

	/**
	 * @return the person
	 */
	public Person getPerson() {
		return person;
	}

	/**
	 * @param person the person to set
	 */
	public void setPerson(Person person) {
		this.person = person;
	}

	/**
	 * @return the dateComentary
	 */
	public LocalDate getDateComentary() {
		return dateComentary;
	}

	/**
	 * @param dateComentary the dateComentary to set
	 */
	public void setDateComentary(LocalDate dateComentary) {
		this.dateComentary = dateComentary;
	}

	/**
	 * @return the comentary
	 */
	public String getComentary() {
		return comentary;
	}

	/**
	 * @param comentary the comentary to set
	 */
	public void setComentary(String comentary) {
		this.comentary = comentary;
	}	
}
