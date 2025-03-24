package com.demo.producer.services;

public interface KafkaProducerService {

	void sendMessage(String topic, String message);
}
