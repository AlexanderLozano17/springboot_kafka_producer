package com.demo.dto.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.ALWAYS)
public class PersonaDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @JsonProperty("id")
    private Long id;
	
    @JsonProperty("nombres")
    private String nombres;
    
    @JsonProperty("apellidos")
    private String apellidos;
    
    @JsonProperty("nombreCompleto")
    private String nombreCompleto;

    @JsonProperty("edad")
    private int edad;
    
    @JsonProperty("email")
    private String email;
    
    @JsonProperty("telefono")
    private String telefono;

    public PersonaDTO(Long id, String nombres, String apellidos, int edad, String email, String telefono) {
    	this.id = id; 
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.nombreCompleto = nombres + " " + apellidos;
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
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nombres
	 */
	public String getNombres() {
		return nombres;
	}

	/**
	 * @param nombres the nombres to set
	 */
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	/**
	 * @return the apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * @param apellidos the apellidos to set
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * @return the edad
	 */
	public int getEdad() {
		return edad;
	}

	/**
	 * @param edad the edad to set
	 */
	public void setEdad(int edad) {
		this.edad = edad;
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
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
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
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	
	 @Override
    public String toString() {
        return "PersonaDTO{" +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", edad=" + edad +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}