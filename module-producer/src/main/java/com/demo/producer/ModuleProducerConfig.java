package com.demo.producer;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.demo.producer.services"})
public class ModuleProducerConfig {

}
