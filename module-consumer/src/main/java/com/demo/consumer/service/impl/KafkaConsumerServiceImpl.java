package com.demo.consumer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.demo.consumer.service.KafkaConsumerService;
import com.demo.core.entities.Mensaje;
import com.demo.core.repositories.MensajeRepository;

@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

	@Autowired
	private MensajeRepository mensajeRepository;
	
	@KafkaListener(topics = "test-topic", groupId = "my-group")
	public void listen(String message) {
		mensajeRepository.save(new Mensaje(message));
		System.out.println("📩 Mensaje recibido desde Kafka: " + message);		
	}
	
	
}
