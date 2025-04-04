package com.demo.dto.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.ALWAYS)
public class ResponseKafka <T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("message")
	private String message;
	
	@JsonProperty("data")
	private T data;

	public ResponseKafka(String message, T data) {
		this.message = message;
		this.data = data;
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
	public T getData() {
		return data;
	}

	/**
	 * @param entidad the entidad to set
	 */
	public void setData(T data) {
		this.data = data;
	}
}
