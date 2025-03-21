package com.demo.entities;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "mensaje")
public class Mensaje {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String contenido;
	
	public Mensaje() {}
	
	public Mensaje(String contenido) {
        this.contenido = contenido;
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return Id;
	}

	/**
	 * @return the contenido
	 */
	public String getContenido() {
		return contenido;
	}

}
