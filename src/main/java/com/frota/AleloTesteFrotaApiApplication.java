package com.frota;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.frota.config.property.AleloFrotaApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(AleloFrotaApiProperty.class)
public class AleloTesteFrotaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AleloTesteFrotaApiApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
