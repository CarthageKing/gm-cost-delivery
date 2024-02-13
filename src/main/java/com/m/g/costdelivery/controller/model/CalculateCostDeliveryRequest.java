package com.m.g.costdelivery.controller.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class CalculateCostDeliveryRequest implements Serializable {

	private static final long serialVersionUID = 6094043982152445189L;

	// use of BigDecimal to precisely control the precision and rounding behavior

	@JsonFormat(shape = Shape.STRING)
	private BigDecimal weight;

	@JsonFormat(shape = Shape.STRING)
	private BigDecimal height;

	@JsonFormat(shape = Shape.STRING)
	private BigDecimal width;

	@JsonFormat(shape = Shape.STRING)
	private BigDecimal length;

	private String voucherCode;

	public CalculateCostDeliveryRequest() {
		// noop
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public BigDecimal getHeight() {
		return height;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	public BigDecimal getWidth() {
		return width;
	}

	public void setWidth(BigDecimal width) {
		this.width = width;
	}

	public BigDecimal getLength() {
		return length;
	}

	public void setLength(BigDecimal length) {
		this.length = length;
	}

	public String getVoucherCode() {
		return voucherCode;
	}

	public void setVoucherCode(String voucherCode) {
		this.voucherCode = voucherCode;
	}
}
