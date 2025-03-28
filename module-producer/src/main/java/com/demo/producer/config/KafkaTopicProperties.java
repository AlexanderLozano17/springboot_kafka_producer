package com.demo.producer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.kafka.topic")
public class KafkaTopicProperties {
	
	private String name;
	private int partitions;
	private short replicas;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the partitions
	 */
	public int getPartitions() {
		return partitions;
	}
	/**
	 * @param partitions the partitions to set
	 */
	public void setPartitions(int partitions) {
		this.partitions = partitions;
	}
	/**
	 * @return the replicas
	 */
	public short getReplicas() {
		return replicas;
	}
	/**
	 * @param replicas the replicas to set
	 */
	public void setReplicas(short replicas) {
		this.replicas = replicas;
	}
}
