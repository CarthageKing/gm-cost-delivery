package com.m.g.costdelivery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CommonConfig {

	public CommonConfig() {
		// noop
	}

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
