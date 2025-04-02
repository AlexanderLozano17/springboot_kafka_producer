package com.demo.main;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.demo.core.ModuleCoreConfig;
import com.demo.producer.ModuleProducerConfig;

@SpringBootApplication
@Import({ModuleCoreConfig.class, ModuleProducerConfig.class})
public class MainApp {
    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }
}