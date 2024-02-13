package com.m.g.costdelivery.util;

import java.math.BigDecimal;

public final class Util {

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
}
