package com.m.g.costdelivery.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.m.g.costdelivery.controller.CostDeliveryController;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackageClasses = { CostDeliveryController.class })
public class TestSpringConfig {

	public TestSpringConfig() {
		// noop
	}
}
