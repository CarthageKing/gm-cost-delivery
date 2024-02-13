package com.m.g.costdelivery.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.m.g.costdelivery.controller.model.ErrorResponse;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

	private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(AppExceptionHandler.class);

	public AppExceptionHandler() {
		// noop
	}

	@ExceptionHandler({ CostDeliveryException.class })
	public ResponseEntity<ErrorResponse> handleAppCustomException(Exception e, WebRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorResponse rsp = new ErrorResponse();
		if (e instanceof BadRequestException) {
			status = HttpStatus.BAD_REQUEST;
			rsp.setError(e.getMessage());
		} else {
			rsp.setError(e.getMessage());
		}
		LOG.error("An error occurred", e);
		return ResponseEntity.status(status).body(rsp);
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<ErrorResponse> handleGenericException(Exception e, WebRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorResponse rsp = new ErrorResponse();
		// for generic exceptions, treat everything as sensitive info and do
		// not leak to the response object
		rsp.setError("An internal error has occurred");
		LOG.error("An error occurred", e);
		return ResponseEntity.status(status).body(rsp);
	}
}
