package com.demo.utils;

/**
 * La clase LogPersona es una clase de constantes utilizada para definir
 * mensajes de log específicos en el módulo de gestión de personas de la
 * aplicación.
 */
public final class LogPerson {

	private LogPerson() {}

	/** ====================================================
					LOGS DE ÉXITO
		==================================================== */
	public static final String PERSON_SAVE_SUCCESS = "Persona guardada exitosamente con ID {}";
	public static final String PERSON_UPDATE_SUCCESS = "Persona actualizada exitosamente con ID {}";
	public static final String PERSON_DELETE_SUCCESS = "Persona eliminada exitosamente con ID {}";
	public static final String PERSON_FOUND = "Persona encontrada con ID {}";
	public static final String PERSON_LIST_SUCCESS = "Listado de personas obtenido correctamente. Total: {}";
	
	/** ====================================================
					LOGS DE ERROR
		==================================================== */
	public static final String PERSON_SAVE_ERROR = "Error al guardar la persona: {}";
	public static final String PERSON_SAVE_ERROR_EMPTY = "No se pudo guardar la persona. Respuesta vacía de PersonService";
	public static final String PERSON_UPDATE_ERROR = "Error al actualizar la persona con ID {}: {}";
	public static final String PERSON_DELETE_ERROR = "Error al eliminar la persona con ID {}: {}";
	public static final String PERSON_NOT_FOUND = "Persona no encontrada con ID {}";
	public static final String PERSON_LIST_ERROR = "Error al obtener el listado de personas: {}";
	
	/** ====================================================
					LOGS DE AUDITORÍA
		==================================================== */
	public static final String REQUEST_RECEIVED = "Solicitud recibida: Método={} URL={} IP={}";
	public static final String REQUEST_BODY = "Cuerpo de la solicitud: {}";
	public static final String RESPONSE_SENT = "Respuesta enviada: Código={} Cuerpo={}";
}
