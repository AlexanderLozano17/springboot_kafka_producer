package com.demo.producer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {
	
	private final KafkaTopicProperties properties;
	
	public KafkaTopicConfig(KafkaTopicProperties kafkaTopicProperties) {
		this.properties = kafkaTopicProperties;
	}
	
	@Bean
	public NewTopic topic() {
		/**
		 * "test-topic" → Nombre del tópico.
		 * 2 → Número de particiones (divide los mensajes para mejorar la escalabilidad).
		 * (short) 2 → Factor de replicación (cuántos brokers almacenarán copias de los mensajes para evitar pérdida de datos). 
		 */
		return new  NewTopic(properties.getName(), properties.getPartitions(), properties.getReplicas());
	}
}
