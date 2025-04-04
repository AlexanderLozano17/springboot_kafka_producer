package com.demo.producer.services.impl;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.demo.producer.services.KafkaProducerComentaryService;
import com.demo.producer.utils.ConstantsKafka;

@Service
public class kafkaProducerComentaryServicesImpl implements KafkaProducerComentaryService {
	
	private static final Logger logger = LoggerFactory.getLogger(kafkaProducerComentaryServicesImpl.class);
	
	private final KafkaTemplate<String, Object> kafkaTemplate;
	
	public kafkaProducerComentaryServicesImpl(KafkaTemplate<String, Object> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	@Override
	public void sendMessageRecordComentary(Object message) {
		
		CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(ConstantsKafka.TOPIC_COMENTARIES, message);
		
		future.whenComplete((result, ex) -> {
			if (ex == null) {
				logger.info("✅📤 Mensaje enviado a Kafka [{}]: {}", ConstantsKafka.TOPIC_COMENTARIES, message);
            } else { 
                // Si hubo un error, lo registramos con detalles
                logger.error("❌ Error al enviar mensaje a Kafka [{}]: {}", ConstantsKafka.TOPIC_COMENTARIES, message, ex);
            }
		});
		
	}

}
