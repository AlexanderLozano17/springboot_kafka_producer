package com.demo.utils;

/**
 * La clase LogMessageKafka es una clase de constantes que define mensajes de
 * log específicos para eventos relacionados con Kafka en una aplicación. Su
 * propósito es estandarizar y centralizar los mensajes de éxito y error
 * generados en la comunicación con Kafka
 */
public final class LogMessageKafka {
	
	private LogMessageKafka() {}
    
	/** ====================================================
    			LOGS DE PRODUCCIÓN (Producer)
		==================================================== */
	public static final String PRODUCER_MESSAGE_SENT = "Produciendo mensaje en tópico=%s con clave=%s";
	public static final String PRODUCER_MESSAGE_SUCCESS = "Mensaje enviado exitosamente a tópico=%s partición=%s offset=%s";
	public static final String PRODUCER_TIMEOUT = "Tiempo de espera al enviar mensaje a Kafka. Reintentando...";
	public static final String PRODUCER_ERROR = "Error al enviar mensaje a Kafka: %s";
	public static final String PRODUCER_CLOSED = "Kafka Producer está cerrado. No se pueden enviar mensajes.";
		
	/** ====================================================
				LOGS DE PARTICIONES Y CONECTIVIDAD
		==================================================== */
	public static final String PARTITION_ASSIGNMENT = "Asignando particiones: %s";
	public static final String PARTITION_REBALANCE = "Rebalanceo detectado. Nueva asignación de particiones.";
	public static final String BROKER_CONNECTION_ERROR = "No se puede conectar al broker Kafka: %s";
	
	/** ====================================================
	    		LOGS DE ERRORES GENERALES
		=================================================== */
	public static final String BROKER_UNAVAILABLE = "Kafka Broker no disponible. Reintentando...";
	public static final String BROKER_TIMEOUT = "Timeout en la comunicación con Kafka.";
	public static final String KAFKA_CRITICAL_ERROR = "Error crítico. Deteniendo el servicio de Kafka.";}
