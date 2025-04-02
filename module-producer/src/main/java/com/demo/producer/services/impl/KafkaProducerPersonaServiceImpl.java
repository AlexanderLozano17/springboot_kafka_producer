package com.demo.producer.services.impl;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.demo.producer.services.KafkaProducerPersonaService;
import com.demo.producer.utils.ConstantesKafka;

@Service
public class KafkaProducerPersonaServiceImpl implements KafkaProducerPersonaService {

	private static final Logger logger = LoggerFactory.getLogger(KafkaProducerPersonaServiceImpl.class);
	
    private final KafkaTemplate<String, Object> kafkaTemplate;
    

    public KafkaProducerPersonaServiceImpl(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

	@Override
	public void sendMessagePersona(Object message) {
		// TODO Auto-generated method stub
		CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(ConstantesKafka.TOPIC_PERSONAS, message);
		
		future.whenComplete((result, ex) -> { 
            // 4️⃣ Verificar si el envío fue exitoso o falló
            if (ex == null) { 
                // Si no hubo errores, loggeamos el mensaje como enviado correctamente
                logger.info("✅📤 Mensaje enviado a Kafka [{}]: {}", ConstantesKafka.TOPIC_PERSONAS, message);
            } else { 
                // Si hubo un error, lo registramos con detalles
                logger.error("❌ Error al enviar mensaje a Kafka [{}]: {}", ConstantesKafka.TOPIC_PERSONAS, message, ex);
            }
        });
		
	}
}
