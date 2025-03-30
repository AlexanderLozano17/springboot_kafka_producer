package com.demo.core.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
	private String nombreCompleto;
	private int edad;
	private String email;
	private String telefono;
	
	@OneToMany(mappedBy = "persona", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Publicacion> publicaciones;
	
	@OneToMany(mappedBy = "persona", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comentario> comentarios;
	
	public Persona() {}
		
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

	/**
	 * @return the nombreCompleto
	 */
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	/**
	 * @param nombreCompleto the nombreCompleto to set
	 */
	public void setNombreCompleto() {
		this.nombreCompleto = nombres + " " + apellidos;
	}
}
