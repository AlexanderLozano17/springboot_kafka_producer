package com.demo.core.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name ="Publication")
public class Publication implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "person_id", nullable = false) // Define la clave foránea
	@JsonBackReference // Esto evita la recursión infinita
	private Person person;
	
	private String title;
	
	@Column(columnDefinition = "TEXT") 
	private String content;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate datePublication;
	
	@OneToMany(mappedBy = "publication", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comentary> comentary;
	
	public Publication() {}
	
    public Publication(Person person, String title, String content) {
        this.person = person;
        this.title = title;
        this.content = content;
    }
	
	@PrePersist // Se ejecuta antes de guardar en la BD
    public void prePersist() {
		datePublication = LocalDate.now(); // Asigna la fecha al crear
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the datePublication
	 */
	public LocalDate getDatePublication() {
		return datePublication;
	}

	/**
	 * @param datePublication the datePublication to set
	 */
	public void setDatePublication(LocalDate datePublication) {
		this.datePublication = datePublication;
	}

	/**
	 * @return the comentary
	 */
	public List<Comentary> getComentary() {
		return comentary;
	}

	/**
	 * @param comentary the comentary to set
	 */
	public void setComentary(List<Comentary> comentary) {
		this.comentary = comentary;
	}
}
