package com.demo.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.producer.services.KafkaProducerService;

@RestController
@RequestMapping("/api/kafka")
public class KafkaController {
	
	@Autowired
	private KafkaProducerService kafkaProducerService;

	@PostMapping("/send")
	public String sendMessage(@RequestParam String topic, @RequestParam String message) {
		kafkaProducerService.sendMessage(topic, message);
		return "✅ Mensaje enviado correctamente!";
	}
}
