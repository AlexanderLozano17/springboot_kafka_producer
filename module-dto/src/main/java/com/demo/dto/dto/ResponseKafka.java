package com.demo.dto.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.ALWAYS)
public class ResponseKafka <T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("message")
	private String message;
	
	@JsonProperty("entidad")
	private T entidad;

	public ResponseKafka(String message, T entity) {
		this.message = message;
		this.entidad = entity;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the entidad
	 */
	public T getEntidad() {
		return entidad;
	}

	/**
	 * @param entidad the entidad to set
	 */
	public void setEntidad(T entidad) {
		this.entidad = entidad;
	}
}
