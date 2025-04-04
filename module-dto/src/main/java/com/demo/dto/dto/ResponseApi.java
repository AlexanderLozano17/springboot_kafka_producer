package com.demo.dto.dto;

import java.io.Serializable;

public class ResponseApi<T> implements Serializable {
	
	 private static final long serialVersionUID = 1L;

	    private String status;   // Código de estado HTTP (Ejemplo: 200, 404, etc.)
	    private String message;   // Mensaje de respuesta (Ejemplo: "Operación exitosa")
	    private T data;           // Datos de la respuesta (puede ser cualquier tipo genérico)

	    public ResponseApi(String status, String message, T data) {
	        this.status = status;
	        this.message = message;
	        this.data = data;
	    }

	    // Getters y setters
	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
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
	                "statusCode=" + status +
	                ", message='" + message + '\'' +
	                ", data=" + data +
	                '}';
	    }
	
}
