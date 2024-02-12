package com.m.g.costdelivery.controller;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.m.g.costdelivery.controller.model.CalculateCostDeliveryRequest;
import com.m.g.costdelivery.controller.model.CalculateCostDeliveryResponse;
import com.m.g.costdelivery.service.CostDeliveryService;

@RequestMapping("/cost_delivery")
@RestController
public class CostDeliveryController {

	@Resource
	private CostDeliveryService costDeliverySvc;

	public CostDeliveryController() {
		// noop
	}

	@PostMapping(value = "/_calculate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CalculateCostDeliveryResponse createSpeech(@RequestBody CalculateCostDeliveryRequest calcCostDeliveryRequest) {
		return costDeliverySvc.calculateCost(calcCostDeliveryRequest);
	}
}
