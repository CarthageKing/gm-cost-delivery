package com.m.g.costdelivery.util;

import java.math.RoundingMode;

public final class AppConstants {

	public static final String RULE_MARKER_ERR = "err:";
	public static final String RULE_MARKER_EXPR = "expr:";

	public static final String RULE_ERR_REJECT = "REJECT";

	// use banker's rounding for internal calculations
	// https://docs.oracle.com/javase/8/docs/api/java/math/RoundingMode.html#HALF_EVEN
	public static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;
	public static final int MAX_BIGDECIMAL_SCALE = 4;

	public static final String MOCK_VOUCHER_API_CONTEXT_PATH = "/mocks/voucher";

	private AppConstants() {
		// noop
	}
}
