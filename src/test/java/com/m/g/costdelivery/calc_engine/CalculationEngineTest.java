package com.m.g.costdelivery.calc_engine;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.m.g.costdelivery.exception.CalculationEngineException;

class CalculationEngineTest {

	private static final BigDecimal TWO = new BigDecimal("2");
	private static final BigDecimal THREE = new BigDecimal("3");
	private static final BigDecimal FOUR = new BigDecimal("4");

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
	void test_operators() {
		{
			String expr = "4 + 0.03";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			BigDecimal result = (BigDecimal) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(new BigDecimal("4.03"), result);
		}
		{
			String expr = "4 - 0.03";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			BigDecimal result = (BigDecimal) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(new BigDecimal("3.97"), result);
		}
	}

	@Test
	void test_externalConstants() throws Exception {
		{
			String expr = "%'weight'";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			BigDecimal result = (BigDecimal) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(new BigDecimal("1"), result);
		}
		{
			String expr = "%'height'";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			BigDecimal result = (BigDecimal) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(new BigDecimal("2"), result);
		}
		{
			String expr = "%'width'";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			BigDecimal result = (BigDecimal) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(new BigDecimal("3"), result);
		}
		{
			String expr = "%'length'";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			BigDecimal result = (BigDecimal) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(new BigDecimal("4"), result);
		}
		{
			String expr = "%'volume'";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			BigDecimal result = (BigDecimal) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(new BigDecimal("24"), result);
		}
		// unrecognized external constant
		{
			String expr = "%'WEIGHT'";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			try {
				ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
				Assertions.fail("did not throw expected exception");
			} catch (CalculationEngineException e) {
				Assertions.assertEquals(true, e.getMessage().contains("Unrecognized variable"));
			}
		}
	}
}
