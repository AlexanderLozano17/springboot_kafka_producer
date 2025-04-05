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
    public static final String PUBLICATION_SAVE_SUCCESS = "Publicación guardada exitosamente con ID %s";
    public static final String PUBLICATION_UPDATE_SUCCESS = "Publicación actualizada exitosamente con ID %s";
    public static final String PUBLICATION_DELETE_SUCCESS = "Publicación eliminada exitosamente con ID %s";
    public static final String PUBLICATION_FOUND = "Publicación encontrada con ID %s";
    public static final String PUBLICATION_LIST_SUCCESS = "Listado de publicaciones obtenido correctamente. Total: %s";

    /** ====================================================
                    LOGS DE ERROR
    	==================================================== */
    public static final String PUBLICATION_SAVE_ERROR = "Error al guardar la publicación: %s";
    public static final String PUBLICATION_SAVE_ERROR_EMPTY = "No se pudo guardar la publicación. Respuesta vacía de PublicationService";
    public static final String PUBLICATION_UPDATE_ERROR = "Error al actualizar la publicación con ID %s: %s";
    public static final String PUBLICATION_DELETE_ERROR = "Error al eliminar la publicación con ID %s: %s";
    public static final String PUBLICATION_NOT_FOUND = "Publicación no encontrada con ID %s";
    public static final String PUBLICATION_LIST_ERROR = "Error al obtener el listado de publicaciones: %s";

    /** ====================================================
                    LOGS DE AUDITORÍA
    	==================================================== */
    public static final String REQUEST_RECEIVED = "Solicitud recibida: Método=%s URL=%s IP=%s";
    public static final String REQUEST_BODY = "Cuerpo de la solicitud: %s";
    public static final String RESPONSE_SENT = "Respuesta enviada: Código=%s Cuerpo=%s";

}
