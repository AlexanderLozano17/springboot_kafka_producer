package com.demo.utils;

/**
 * La clase Commentary es una clase de constantes utilizada para definir
 * mensajes de log específicos en el módulo de gestión de comentarios de la
 * aplicación.
 */
public final class LogCommentary {

    private void LogCommentary() {}

    /** ====================================================
                    LOGS DE ÉXITO
    ==================================================== */
    public static final String COMMENT_SAVE_SUCCESS = "Comentario guardado exitosamente con ID {}";
    public static final String COMMENT_UPDATE_SUCCESS = "Comentario actualizado exitosamente con ID {}";
    public static final String COMMENT_DELETE_SUCCESS = "Comentario eliminado exitosamente con ID {}";
    public static final String COMMENT_FOUND = "Comentario encontrado con ID {}";
    public static final String COMMENT_LIST_SUCCESS = "Listado de comentarios obtenido correctamente. Total: {}";

    /** ====================================================
                    LOGS DE ERROR
    ==================================================== */
    public static final String COMMENT_SAVE_ERROR = "Error al guardar el comentario: {}";
    public static final String COMMENT_SAVE_ERROR_EMPTY = "No se pudo guardar el comentario. Respuesta vacía de CommentService";
    public static final String COMMENT_UPDATE_ERROR = "Error al actualizar el comentario con ID {}: {}";
    public static final String COMMENT_DELETE_ERROR = "Error al eliminar el comentario con ID {}: {}";
    public static final String COMMENT_NOT_FOUND = "Comentario no encontrado con ID {}";
    public static final String COMMENT_LIST_ERROR = "Error al obtener el listado de comentarios: {}";

    /** ====================================================
                    LOGS DE AUDITORÍA
    ==================================================== */
    public static final String REQUEST_RECEIVED = "Solicitud recibida: Método={} URL={} IP={}";
    public static final String REQUEST_BODY = "Cuerpo de la solicitud: {}";
    public static final String RESPONSE_SENT = "Respuesta enviada: Código={} Cuerpo={}";
}
