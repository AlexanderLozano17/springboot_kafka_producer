package com.demo.main.config;

import java.util.ArrayList;
import java.util.List;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;

@Configuration
public class OpenApiConfig {
	
	@Value("${spring.application.name}")
	private  static String appName;
	
	private final static String baseUrl = "http://localhost:8081/";
	
	private static final List<Server> servers = List.of(
			new Server().url(baseUrl + appName).description("DEV SERVER").url("http://localhost:8081"),
			new Server().description("PRO SERVER").url("")
			);
		
	@Bean
    public OpenAPI customOpenApi() {
		
        return new OpenAPI()
                .info(new Info()
                        .title(appName +" API RODUCER")
                        .version("1.0.0")
                        .description("""
                            Esta API está construida con Spring Boot y Apache Kafka para producción de mensajes.
                            
                            ### Características:
                            - Envío de mensajes a Kafka mediante POST
                            - Documentación generada automáticamente con springdoc-openapi
                        """))
                .servers(servers);
	}
	
	@Bean
	public GroupedOpenApi kafkaGroup() {
		return GroupedOpenApi.builder()
				.group(appName + " API")
				.pathsToMatch("/api/person/**", "/api/publication/**", "/api/commentary/**")
				.build();
	}

}
