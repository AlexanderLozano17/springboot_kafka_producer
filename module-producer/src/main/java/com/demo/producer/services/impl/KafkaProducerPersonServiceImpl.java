package com.demo.producer.services.impl;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.demo.producer.services.KafkaProducerPersonService;
import com.demo.producer.utils.ConstantsKafka;
import com.demo.utils.LogHelper;
import com.demo.utils.LogMessageKafka;

@Service
public class KafkaProducerPersonServiceImpl implements KafkaProducerPersonService {

	private static final Logger logger = LoggerFactory.getLogger(KafkaProducerPersonServiceImpl.class);
	
    private final KafkaTemplate<String, Object> kafkaTemplate;
    

    public KafkaProducerPersonServiceImpl(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

	@Override
	public void sendMessageRecordPerson(Object message) {
		logger.info(LogHelper.start(getClass(), String.format(LogMessageKafka.PRODUCER_MESSAGE_SENT, ConstantsKafka.TOPIC_PERSONS, "")));
		
		CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(ConstantsKafka.TOPIC_PERSONS, message);
		
		future.whenComplete((result, ex) -> { 
            // 4️⃣ Verificar si el envío fue exitoso o falló
            if (ex == null) { 
                // Si no hubo errores, loggeamos el mensaje como enviado correctamente
            	logger.info(LogHelper.success(getClass(), "sendMessageRecordPerson", String.format(LogMessageKafka.PRODUCER_MESSAGE_SUCCESS, ConstantsKafka.TOPIC_PERSONS)));
            } else { 
                // Si hubo un error, lo registramos con detalles
            	logger.error(LogHelper.error(getClass(), "sendMessageRecordPerson", String.format(LogMessageKafka.PRODUCER_ERROR, ex)));
            }
        });
		
	}
}
