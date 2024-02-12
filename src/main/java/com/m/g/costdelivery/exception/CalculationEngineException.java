package com.m.g.costdelivery.exception;

public class CalculationEngineException extends CostDeliveryException {

	private static final long serialVersionUID = 4719940780113978289L;

	public CalculationEngineException(String msg) {
		super(msg);
	}

	public CalculationEngineException(Throwable cause) {
		super(cause);
	}

	public CalculationEngineException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
