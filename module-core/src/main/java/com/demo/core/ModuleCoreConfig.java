package com.demo.core;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {"com.demo.core.services"})
@EntityScan(basePackages = "com.demo.core.entities")
@EnableJpaRepositories(basePackages = "com.demo.core.repositories")
public class ModuleCoreConfig {

}
