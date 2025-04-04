package com.demo.core.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name ="person")
public class Person implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	private String names;
	
	private String lastNames;
	private int age;
	
	@Email(message = "El email debe ser válido")
    @NotNull(message = "El email no puede ser nulo")
    @Column(unique = true)  // Asegura que el email sea único en la base de datos
	private String email;
	
	@Column(length = 10)
	private String telephone;
	
	@OneToMany(mappedBy = "person", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Publication> publications;
	
	@OneToMany(mappedBy = "person", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Commentary> commentaries;
	
	public Person() {}

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
	 * @return the names
	 */
	public String getNames() {
		return names;
	}

	/**
	 * @param names the names to set
	 */
	public void setNames(String names) {
		this.names = names;
	}

	/**
	 * @return the lastNames
	 */
	public String getLastNames() {
		return lastNames;
	}

	/**
	 * @param lastNames the lastNames to set
	 */
	public void setLastNames(String lastNames) {
		this.lastNames = lastNames;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * @return the publications
	 */
	public List<Publication> getPublications() {
		return publications;
	}

	/**
	 * @param publications the publications to set
	 */
	public void setPublications(List<Publication> publications) {
		this.publications = publications;
	}

	/**
	 * @return the comentaries
	 */
	public List<Commentary> getComentaries() {
		return commentaries;
	}

	/**
	 * @param comentaries the comentaries to set
	 */
	public void setComentaries(List<Commentary> commentaries) {
		this.commentaries = commentaries;
	}
}
