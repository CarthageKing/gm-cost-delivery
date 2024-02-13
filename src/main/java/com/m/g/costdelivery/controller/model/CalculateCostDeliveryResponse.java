package com.m.g.costdelivery.controller.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class CalculateCostDeliveryResponse implements Serializable {

	private static final long serialVersionUID = 6094043982152445189L;

	// use of BigDecimal to precisely control the precision and rounding behavior
	@JsonFormat(shape = Shape.STRING)
	private BigDecimal cost;

	public CalculateCostDeliveryResponse() {
		// noop
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
}
