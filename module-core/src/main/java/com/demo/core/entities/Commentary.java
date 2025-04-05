package com.demo.core.entities;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "commentary")
public class Commentary implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "publication_id") // Define la clave foranea
	@JsonBackReference(value = "publication-commentary")
	private Publication publication;
	
	@ManyToOne
	@JoinColumn(name = "person_id") // Define la clave foranea
	@JsonBackReference(value = "person-commentary")
	private Person person;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dateCommentary;
	
	private String content;
	
	public Commentary() {}
	
	@PrePersist
	public void prePersist() {
		this.dateCommentary = LocalDate.now();
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
	 * @return the dateCommentary
	 */
	public LocalDate getDateCommentary() {
		return dateCommentary;
	}

	/**
	 * @param dateCommentary the dateCommentary to set
	 */
	public void setDateCommentary(LocalDate dateCommentary) {
		this.dateCommentary = dateCommentary;
	}

	/**
	 * @return the commentary
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param commentary the commentary to set
	 */
	public void setContent(String content) {
		this.content = content;
	}	
}
