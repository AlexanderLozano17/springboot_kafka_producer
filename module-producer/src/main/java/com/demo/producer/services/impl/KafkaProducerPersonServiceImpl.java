package com.demo.producer.services.impl;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.demo.producer.services.KafkaProducerPersonService;
import com.demo.producer.utils.ConstantsKafka;

@Service
public class KafkaProducerPersonServiceImpl implements KafkaProducerPersonService {

	private static final Logger logger = LoggerFactory.getLogger(KafkaProducerPersonServiceImpl.class);
	
    private final KafkaTemplate<String, Object> kafkaTemplate;
    

    public KafkaProducerPersonServiceImpl(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

	@Override
	public void sendMessagePerson(Object message) {
		// TODO Auto-generated method stub
		CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(ConstantsKafka.TOPIC_PERSONAS, message);
		
		future.whenComplete((result, ex) -> { 
            // 4️⃣ Verificar si el envío fue exitoso o falló
            if (ex == null) { 
                // Si no hubo errores, loggeamos el mensaje como enviado correctamente
                logger.info("✅📤 Mensaje enviado a Kafka [{}]: {}", ConstantsKafka.TOPIC_PERSONAS, message);
            } else { 
                // Si hubo un error, lo registramos con detalles
                logger.error("❌ Error al enviar mensaje a Kafka [{}]: {}", ConstantsKafka.TOPIC_PERSONAS, message, ex);
            }
        });
		
	}
}
