package com.m.g.costdelivery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class CommonConfig {

	public CommonConfig() {
		// noop
	}

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	// for parsing LocalDate
	@Bean
	Module javaTimeModule() {
		return new JavaTimeModule();
	}
}
