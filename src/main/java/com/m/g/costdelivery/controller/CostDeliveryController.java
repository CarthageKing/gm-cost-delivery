package com.m.g.costdelivery.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/cost_delivery")
@RestController
public class CostDeliveryController {

	public CostDeliveryController() {
		// noop
	}

	@GetMapping(value = "/_calculate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String createSpeech() {
		return "computed";
	}
}
