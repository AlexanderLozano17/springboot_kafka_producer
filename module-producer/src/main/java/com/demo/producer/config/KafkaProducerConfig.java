package com.demo.producer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaProducerConfig {

	private final KafkaProperties properties;
	
	public KafkaProducerConfig(KafkaProperties kafkaProperties) {
		this.properties = kafkaProperties;
	}
		
	@Bean
	public ProducerFactory<String, Object> producerFactory() {
		Map<String, Object> config = new HashMap<>();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers()); //  Define los servidores de Kafka a los que se conectará el productor.
        config.put(ProducerConfig.RETRIES_CONFIG, properties.getProducer().getRetries()); // Número de intentos en caso de fallo al enviar un mensaje.
        config.put(ProducerConfig.BATCH_SIZE_CONFIG, properties.getProducer().getBatchSize()); // Tamaño máximo del lote de mensajes antes de enviarlos.
        config.put(ProducerConfig.BUFFER_MEMORY_CONFIG, properties.getProducer().getBufferMemory()); // Memoria reservada para mensajes antes de ser enviados a Kafka.
        config.put(ProducerConfig.LINGER_MS_CONFIG, properties.getProducer().getLingerMs()); // Tiempo que espera antes de enviar un lote (en milisegundos).
        config.put(ProducerConfig.ACKS_CONFIG, properties.getProducer().getAcks()); // 	Nivel de confirmación: "all" significa que todos los nodos deben confirmar la recepción del mensaje.
        config.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, properties.getProducer().isEnableIdempotence()); // Habilita la idempotencia, evitando duplicados en los mensajes.
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class); // Serializa la clave del mensaje en formato String
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class); // Serializa el valor del mensaje en formato Objecto.
        return new DefaultKafkaProducerFactory<>(config);
	}
		
	/**
	 *🔹KafkaTemplate es el componente principal para enviar mensajes en Kafka.
	 *🔹Usa el ProducerFactory definido anteriormente.
	 *🔹Permite a los servicios enviar mensajes de forma de Objeto.
	 * @return
	 */
	@Bean
	public KafkaTemplate<String, Object> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}
}
