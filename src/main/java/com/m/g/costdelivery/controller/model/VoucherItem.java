package com.m.g.costdelivery.controller.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class VoucherItem implements Serializable {

	private static final long serialVersionUID = 3484086673811147805L;

	private String code;

	// do not use JsonFormat annotation here so that Jackson can format it to the JSON number type
	private BigDecimal discount;

	// the annotation is to tell Jackson to format this as a string when writing out the JSON
	@JsonFormat(shape = Shape.STRING)
	private LocalDate expiry;

	public VoucherItem() {
		// noop
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public LocalDate getExpiry() {
		return expiry;
	}

	public void setExpiry(LocalDate expiry) {
		this.expiry = expiry;
	}
}
