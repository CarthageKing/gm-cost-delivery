package com.m.g.costdelivery.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.m.g.costdelivery.calc_engine.dao.CostDeliveryRuleDao;
import com.m.g.costdelivery.controller.CostDeliveryController;
import com.m.g.costdelivery.exception.AppExceptionHandler;
import com.m.g.costdelivery.service.CostDeliveryService;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackageClasses = { CostDeliveryController.class, CostDeliveryService.class, CostDeliveryRuleDao.class, AppExceptionHandler.class })
public class TestSpringConfig {

	public TestSpringConfig() {
		// noop
	}
}
