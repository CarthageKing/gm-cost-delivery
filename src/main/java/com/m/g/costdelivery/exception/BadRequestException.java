package com.m.g.costdelivery.exception;

public class BadRequestException extends CostDeliveryException {

	private static final long serialVersionUID = 4719940780113978289L;

	public BadRequestException(String msg) {
		super(msg);
	}

	public BadRequestException(Throwable cause) {
		super(cause);
	}

	public BadRequestException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
