package com.demo.services;

public interface KafkaProducerService {

	void sendMessage(String topic, String message);
}
