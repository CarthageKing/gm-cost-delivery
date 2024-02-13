package com.m.g.costdelivery.controller.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class ErrorResponse implements Serializable {

	private static final long serialVersionUID = 1560248590397213643L;

	private String error;

	public ErrorResponse() {
		// noop
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
