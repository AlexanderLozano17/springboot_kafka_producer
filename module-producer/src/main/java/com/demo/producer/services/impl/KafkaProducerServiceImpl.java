package com.demo.producer.services.impl;

import java.util.concurrent.CompletableFuture;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.demo.producer.services.KafkaProducerService;

@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {

	private static final Logger logger = LoggerFactory.getLogger(KafkaProducerServiceImpl.class);
	
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, String message) {
    	
    	 // Crear un objeto ProducerRecord que representa el mensaje a enviar a Kafka
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, message);
        	
        //  Enviar el mensaje de manera asíncrona utilizando KafkaTemplate
        //  - Devuelve un CompletableFuture que nos permite manejar la respuesta más adelante
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(record);
        	
        // Definir el comportamiento cuando la operación de envío se complete
        future.whenComplete((result, ex) -> { 
            // 4️⃣ Verificar si el envío fue exitoso o falló
            if (ex == null) { 
                // Si no hubo errores, loggeamos el mensaje como enviado correctamente
                logger.info("✅📤 Mensaje enviado a Kafka [{}]: {}", topic, message);
            } else { 
                // Si hubo un error, lo registramos con detalles
                logger.error("❌ Error al enviar mensaje a Kafka [{}]: {}", topic, message, ex);
            }
        });
    }
}
