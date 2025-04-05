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
    public static final String COMMENT_SAVE_SUCCESS = "Comentario guardado exitosamente con ID %s";
    public static final String COMMENT_UPDATE_SUCCESS = "Comentario actualizado exitosamente con ID %s";
    public static final String COMMENT_DELETE_SUCCESS = "Comentario eliminado exitosamente con ID %s";
    public static final String COMMENT_FOUND = "Comentario encontrado con ID %s";
    public static final String COMMENT_LIST_SUCCESS = "Listado de comentarios obtenido correctamente. Total: %s";

    /** ====================================================
                    LOGS DE ERROR
    ==================================================== */
    public static final String COMMENT_SAVE_ERROR = "Error al guardar el comentario: %s";
    public static final String COMMENT_SAVE_ERROR_EMPTY = "No se pudo guardar el comentario. Respuesta vacía de CommentService";
    public static final String COMMENT_UPDATE_ERROR = "Error al actualizar el comentario con ID %s: %s";
    public static final String COMMENT_DELETE_ERROR = "Error al eliminar el comentario con ID %s: %s";
    public static final String COMMENT_NOT_FOUND = "Comentario no encontrado con ID %s";
    public static final String COMMENT_LIST_ERROR = "Error al obtener el listado de comentarios: %s";

    /** ====================================================
                    LOGS DE AUDITORÍA
    ==================================================== */
    public static final String REQUEST_RECEIVED = "Solicitud recibida: Método=%s URL=%s IP=%s";
    public static final String REQUEST_BODY = "Cuerpo de la solicitud: %s";
    public static final String RESPONSE_SENT = "Respuesta enviada: Código=%s Cuerpo=%s";
}
