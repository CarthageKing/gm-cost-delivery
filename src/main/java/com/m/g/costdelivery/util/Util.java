package com.m.g.costdelivery.util;

import java.math.BigDecimal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.m.g.costdelivery.exception.CostDeliveryException;

public final class Util {

	private static final ObjectMapper OBJECT_MAPPER;

	static {
		OBJECT_MAPPER = new ObjectMapper().registerModule(new JavaTimeModule());
	}

	private Util() {
		// noop
	}

	public static BigDecimal stripTrailingZerosAfterDecimalPoint(BigDecimal origVal) {
		if (origVal.scale() <= 0) {
			return origVal;
		}
		String txt = origVal.toPlainString();
		int idx = txt.lastIndexOf('.');
		if (idx >= 0) {
			for (int i = txt.length() - 1; i >= idx; i--) {
				if ('0' != txt.charAt(i)) {
					if ('.' == txt.charAt(i)) {
						return new BigDecimal(txt.substring(0, i));
					} else
						return new BigDecimal(txt.substring(0, i + 1));
				}
			}
		}
		return origVal;
	}

	public static <T> T toObj(String jsonStr, Class<T> clazz) {
		try {
			return OBJECT_MAPPER.readValue(jsonStr, clazz);
		} catch (JsonProcessingException e) {
			throw new CostDeliveryException("JSON deserialization error occurred", e);
		}
	}

	public static String toStr(Object obj) {
		try {
			return OBJECT_MAPPER.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			throw new CostDeliveryException("JSON serialization error occurred", e);
		}
	}
}
