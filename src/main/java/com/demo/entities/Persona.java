package com.demo.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name ="persona")
public class Persona implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	private String nombres;
	private String apellidos;
	private int edad;
	private String email;
	private String telefono;
	
	@OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Publicacion> publicaciones;
	
	@OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comentario> comentarios;
	
	public Persona() {}
	
	public Persona(String nombres, String apellidos, int edad, String email, String telefono) {
		super();
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.edad = edad;
		this.email = email;
		this.telefono = telefono;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the nombres
	 */
	public String getNombres() {
		return nombres;
	}

	/**
	 * @return the apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * @return the edad
	 */
	public int getEdad() {
		return edad;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}
	
    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }
	
	public void setPublicaciones(List<Publicacion> publicaciones) {
		this.publicaciones = publicaciones;
	}

	/**
	 * @return the comentarios
	 */
	public List<Comentario> getComentarios() {
		return comentarios;
	}

	/**
	 * @param comentarios the comentarios to set
	 */
	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
}
