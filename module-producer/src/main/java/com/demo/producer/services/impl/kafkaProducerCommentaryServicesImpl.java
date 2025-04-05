package com.demo.producer.services.impl;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.demo.producer.services.KafkaProducerCommentaryService;
import com.demo.producer.utils.ConstantsKafka;
import com.demo.utils.LogHelper;
import com.demo.utils.LogMessageKafka;

@Service
public class kafkaProducerCommentaryServicesImpl implements KafkaProducerCommentaryService {
	
	private static final Logger logger = LoggerFactory.getLogger(kafkaProducerCommentaryServicesImpl.class);
	
	private final KafkaTemplate<String, Object> kafkaTemplate;
	
	public kafkaProducerCommentaryServicesImpl(KafkaTemplate<String, Object> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	@Override
	public void sendMessageRecordCommentary(Object message) {
		logger.info(LogHelper.start(getClass(), String.format(LogMessageKafka.PRODUCER_MESSAGE_SENT, ConstantsKafka.TOPIC_COMMENTARIES, "")));
		
		CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(ConstantsKafka.TOPIC_COMMENTARIES, message);
		
		future.whenComplete((result, ex) -> {
			if (ex == null) {
				logger.info(LogHelper.success(getClass(), "sendMessageRecordComentary", String.format(LogMessageKafka.PRODUCER_MESSAGE_SUCCESS, ConstantsKafka.TOPIC_COMMENTARIES)));
            } else { 
                // Si hubo un error, lo registramos con detalles
            	logger.error(LogHelper.error(getClass(), "sendMessageRecordComentary", String.format(LogMessageKafka.PRODUCER_ERROR, ex.getMessage())), ex);
            }
		});
		
	}

}
