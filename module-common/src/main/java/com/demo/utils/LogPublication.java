package com.demo.utils;

/**
 * La clase LogPublication es un repositorio de constantes utilizadas para
 * estandarizar los mensajes de log en el módulo de publicaciones.
 */
public final class LogPublication {

	private LogPublication() {}

    /** ====================================================
                    LOGS DE ÉXITO
    	==================================================== */
    public static final String PUBLICATION_SAVE_SUCCESS = "Publicación guardada exitosamente con ID {}";
    public static final String PUBLICATION_UPDATE_SUCCESS = "Publicación actualizada exitosamente con ID {}";
    public static final String PUBLICATION_DELETE_SUCCESS = "Publicación eliminada exitosamente con ID {}";
    public static final String PUBLICATION_FOUND = "Publicación encontrada con ID {}";
    public static final String PUBLICATION_LIST_SUCCESS = "Listado de publicaciones obtenido correctamente. Total: {}";

    /** ====================================================
                    LOGS DE ERROR
    	==================================================== */
    public static final String PUBLICATION_SAVE_ERROR = "Error al guardar la publicación: {}";
    public static final String PUBLICATION_SAVE_ERROR_EMPTY = "No se pudo guardar la publicación. Respuesta vacía de PublicationService";
    public static final String PUBLICATION_UPDATE_ERROR = "Error al actualizar la publicación con ID {}: {}";
    public static final String PUBLICATION_DELETE_ERROR = "Error al eliminar la publicación con ID {}: {}";
    public static final String PUBLICATION_NOT_FOUND = "Publicación no encontrada con ID {}";
    public static final String PUBLICATION_LIST_ERROR = "Error al obtener el listado de publicaciones: {}";

    /** ====================================================
                    LOGS DE AUDITORÍA
    	==================================================== */
    public static final String REQUEST_RECEIVED = "Solicitud recibida: Método={} URL={} IP={}";
    public static final String REQUEST_BODY = "Cuerpo de la solicitud: {}";
    public static final String RESPONSE_SENT = "Respuesta enviada: Código={} Cuerpo={}";

}
