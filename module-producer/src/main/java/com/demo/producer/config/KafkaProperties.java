package com.demo.producer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.kafka.producer") // arga automáticamente todas las propiedades que empiezan con el prefijo spring.kafka.producer desde el archivo application.yml o application.properties.
public class KafkaProperties {

    private String bootstrapServers;
    private int retries;
    private int batchSize;
    private int bufferMemory;
    private int lingerMs;
    private String acks;
    private boolean enableIdempotence;
    private String keySerializer;
    private String valueSerializer;
	/**
	 * @return the bootstrapServers
	 */
	public String getBootstrapServers() {
		return bootstrapServers;
	}
	/**
	 * @param bootstrapServers the bootstrapServers to set
	 */
	public void setBootstrapServers(String bootstrapServers) {
		this.bootstrapServers = bootstrapServers;
	}
	/**
	 * @return the retries
	 */
	public int getRetries() {
		return retries;
	}
	/**
	 * @param retries the retries to set
	 */
	public void setRetries(int retries) {
		this.retries = retries;
	}
	/**
	 * @return the batchSize
	 */
	public int getBatchSize() {
		return batchSize;
	}
	/**
	 * @param batchSize the batchSize to set
	 */
	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}
	/**
	 * @return the bufferMemory
	 */
	public int getBufferMemory() {
		return bufferMemory;
	}
	/**
	 * @param bufferMemory the bufferMemory to set
	 */
	public void setBufferMemory(int bufferMemory) {
		this.bufferMemory = bufferMemory;
	}
	/**
	 * @return the lingerMs
	 */
	public int getLingerMs() {
		return lingerMs;
	}
	/**
	 * @param lingerMs the lingerMs to set
	 */
	public void setLingerMs(int lingerMs) {
		this.lingerMs = lingerMs;
	}
	/**
	 * @return the acks
	 */
	public String getAcks() {
		return acks;
	}
	/**
	 * @param acks the acks to set
	 */
	public void setAcks(String acks) {
		this.acks = acks;
	}
	/**
	 * @return the enableIdempotence
	 */
	public boolean isEnableIdempotence() {
		return enableIdempotence;
	}
	/**
	 * @param enableIdempotence the enableIdempotence to set
	 */
	public void setEnableIdempotence(boolean enableIdempotence) {
		this.enableIdempotence = enableIdempotence;
	}
	/**
	 * @return the keySerializer
	 */
	public String getKeySerializer() {
		return keySerializer;
	}
	/**
	 * @param keySerializer the keySerializer to set
	 */
	public void setKeySerializer(String keySerializer) {
		this.keySerializer = keySerializer;
	}
	/**
	 * @return the valueSerializer
	 */
	public String getValueSerializer() {
		return valueSerializer;
	}
	/**
	 * @param valueSerializer the valueSerializer to set
	 */
	public void setValueSerializer(String valueSerializer) {
		this.valueSerializer = valueSerializer;
	}
}
