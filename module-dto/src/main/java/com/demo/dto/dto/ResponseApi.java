package com.demo.dto.dto;

import java.io.Serializable;

public class ResponseApi<T> implements Serializable {
	
	 private static final long serialVersionUID = 1L;

	    private int statusCode;   // Código de estado HTTP (Ejemplo: 200, 404, etc.)
	    private String message;   // Mensaje de respuesta (Ejemplo: "Operación exitosa")
	    private T data;           // Datos de la respuesta (puede ser cualquier tipo genérico)

	    public ResponseApi(int statusCode, String message, T data) {
	        this.statusCode = statusCode;
	        this.message = message;
	        this.data = data;
	    }

	    // Getters y setters
	    public int getStatusCode() {
	        return statusCode;
	    }

	    public void setStatusCode(int statusCode) {
	        this.statusCode = statusCode;
	    }

	    public String getMessage() {
	        return message;
	    }

	    public void setMessage(String message) {
	        this.message = message;
	    }

	    public T getData() {
	        return data;
	    }

	    public void setData(T data) {
	        this.data = data;
	    }

	    @Override
	    public String toString() {
	        return "ApiResponse{" +
	                "statusCode=" + statusCode +
	                ", message='" + message + '\'' +
	                ", data=" + data +
	                '}';
	    }
	
}
