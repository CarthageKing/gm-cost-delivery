package com.m.g.costdelivery.exception;

public class CostDeliveryException extends RuntimeException {

	private static final long serialVersionUID = 4719940780113978289L;

	public CostDeliveryException(String msg) {
		super(msg);
	}

	public CostDeliveryException(Throwable cause) {
		super(cause);
	}

	public CostDeliveryException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
