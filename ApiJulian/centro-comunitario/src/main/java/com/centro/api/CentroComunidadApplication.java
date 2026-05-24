package com.centro.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class CentroComunidadApplication {
    public static void main(String[] args) {
        SpringApplication.run(CentroComunidadApplication.class, args);
    }
}
