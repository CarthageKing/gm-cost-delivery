package com.m.g.costdelivery.util;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UtilTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		Assertions.assertEquals("60", Util.stripTrailingZerosAfterDecimalPoint(new BigDecimal("60")).toPlainString());
		Assertions.assertEquals("60.1", Util.stripTrailingZerosAfterDecimalPoint(new BigDecimal("60.1")).toPlainString());
		Assertions.assertEquals("60.1", Util.stripTrailingZerosAfterDecimalPoint(new BigDecimal("60.10")).toPlainString());
		Assertions.assertEquals("60", Util.stripTrailingZerosAfterDecimalPoint(new BigDecimal("60.0")).toPlainString());
	}
}
