package com.demo.producer.services.impl;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.demo.producer.services.KafkaProducerPublicationService;
import com.demo.producer.utils.ConstantsKafka;
import com.demo.utils.LogHelper;
import com.demo.utils.LogMessageKafka;

@Service
public class KafkaProducerPublicationServiceImpl implements KafkaProducerPublicationService {

	private static final Logger logger = LoggerFactory.getLogger(KafkaProducerPublicationServiceImpl.class);
	
	private final KafkaTemplate<String, Object> kafkaTemplate;
	
	public KafkaProducerPublicationServiceImpl(KafkaTemplate<String, Object> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
	
	@Override
	public void sendMessageRecordPublication(Object message) {
		logger.info(LogHelper.start(getClass(), String.format(LogMessageKafka.PRODUCER_MESSAGE_SENT, ConstantsKafka.TOPIC_PUBLICATIONS, "")));
		
		CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(ConstantsKafka.TOPIC_PUBLICATIONS, message);
		
		future.whenComplete((result, ex) -> {
			if (ex == null) {
				logger.info(LogHelper.success(getClass(), "sendMessageRecordPublication", String.format(LogMessageKafka.PRODUCER_MESSAGE_SUCCESS, ConstantsKafka.TOPIC_PUBLICATIONS)));
            } else { 
                // Si hubo un error, lo registramos con detalles
            	logger.error(LogHelper.error(getClass(), "sendMessageRecordPublication", String.format(LogMessageKafka.PRODUCER_ERROR, ex.getMessage())), ex);
            }
		});
	}

}
