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
	void test_literals() throws Exception {
		{
			String expr = "true";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			boolean result = (boolean) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(true, result);
		}
		{
			String expr = "false";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			boolean result = (boolean) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(false, result);
		}
		{
			String expr = "8.75";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			BigDecimal result = (BigDecimal) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(new BigDecimal("8.75"), result);
		}
	}

	@Test
	void test_operators() throws Exception {
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
		{
			String expr = "4+5";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			BigDecimal result = (BigDecimal) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(new BigDecimal(4 + 5), result);
		}
		{
			String expr = "4+true";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			try {
				ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
				Assertions.fail("did not throw expected exception");
			} catch (CalculationEngineException e) {
				Assertions.assertEquals(true, e.getMessage().contains("Incompatible types for operation"));
			}
		}
		{
			String expr = "false+5";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			try {
				ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
				Assertions.fail("did not throw expected exception");
			} catch (CalculationEngineException e) {
				Assertions.assertEquals(true, e.getMessage().contains("Incompatible types for operation"));
			}
		}
		{
			String expr = "(4+5)";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			BigDecimal result = (BigDecimal) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(new BigDecimal(4 + 5), result);
		}
		{
			String expr = "4+5*2-6/2";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			BigDecimal result = (BigDecimal) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(new BigDecimal(4 + 5 * 2 - 6 / 2), result);
		}
		{
			String expr = "(4+5)*(2-6)/2";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			BigDecimal result = (BigDecimal) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(new BigDecimal((4 + 5) * (2 - 6) / 2), result);
		}
		{
			String expr = "((4+5*2)-6)/2";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			BigDecimal result = (BigDecimal) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(new BigDecimal(((4 + 5 * 2) - 6) / 2), result);
		}
		{
			String expr = "6 mod 4";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			BigDecimal result = (BigDecimal) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(new BigDecimal(6 % 4), result);
		}
		{
			String expr = "false mod 5";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			try {
				ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
				Assertions.fail("did not throw expected exception");
			} catch (CalculationEngineException e) {
				Assertions.assertEquals(true, e.getMessage().contains("Incompatible types for operation"));
			}
		}
		{
			String expr = "5 mod true";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			try {
				ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
				Assertions.fail("did not throw expected exception");
			} catch (CalculationEngineException e) {
				Assertions.assertEquals(true, e.getMessage().contains("Incompatible types for operation"));
			}
		}

		{
			String expr = "4<5";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			boolean result = (boolean) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(true, result);
		}
		{
			String expr = "5<4";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			boolean result = (boolean) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(false, result);
		}
		{
			String expr = "4<=5";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			boolean result = (boolean) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(true, result);
		}
		{
			String expr = "5<=4";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			boolean result = (boolean) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(false, result);
		}
		{
			String expr = "4<=4";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			boolean result = (boolean) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(true, result);
		}

		{
			String expr = "4>5";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			boolean result = (boolean) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(false, result);
		}
		{
			String expr = "5>4";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			boolean result = (boolean) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(true, result);
		}
		{
			String expr = "4>=5";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			boolean result = (boolean) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(false, result);
		}
		{
			String expr = "5>=4";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			boolean result = (boolean) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(true, result);
		}
		{
			String expr = "4>=4";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			boolean result = (boolean) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(true, result);
		}
		{
			String expr = "4>true";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			try {
				ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
				Assertions.fail("did not throw expected exception");
			} catch (CalculationEngineException e) {
				Assertions.assertEquals(true, e.getMessage().contains("Incompatible types for operation"));
			}
		}
		{
			String expr = "false<5";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			try {
				ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
				Assertions.fail("did not throw expected exception");
			} catch (CalculationEngineException e) {
				Assertions.assertEquals(true, e.getMessage().contains("Incompatible types for operation"));
			}
		}

		{
			String expr = "true=true";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			boolean result = (boolean) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(true, result);
		}
		{
			String expr = "true=false";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			boolean result = (boolean) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(false, result);
		}
		{
			String expr = "true!=true";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			boolean result = (boolean) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(false, result);
		}
		{
			String expr = "true!=false";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			boolean result = (boolean) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(true, result);
		}
		{
			String expr = "4=5";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			boolean result = (boolean) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(false, result);
		}
		{
			String expr = "4=4";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			boolean result = (boolean) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(true, result);
		}
		{
			String expr = "4.0=4.00";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			boolean result = (boolean) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(true, result);
		}
		{
			String expr = "4.75=4.750";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			boolean result = (boolean) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(true, result);
		}
		{
			String expr = "4.75=4.751";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			boolean result = (boolean) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(false, result);
		}
		{
			String expr = "4.75!=4.751";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			boolean result = (boolean) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(true, result);
		}
		{
			String expr = "4.75!=4.750000000000000000000100";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			boolean result = (boolean) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(true, result);
		}
		{
			String expr = "4=true";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			try {
				ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
				Assertions.fail("did not throw expected exception");
			} catch (CalculationEngineException e) {
				Assertions.assertEquals(true, e.getMessage().contains("Incompatible types for operation"));
			}
		}

		{
			String expr = "true and true";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			boolean result = (boolean) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(true, result);
		}
		{
			String expr = "true and false";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			boolean result = (boolean) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(false, result);
		}
		{
			String expr = "false and true";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			boolean result = (boolean) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(false, result);
		}
		{
			String expr = "false and false";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			boolean result = (boolean) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(false, result);
		}
		{
			String expr = "4 and true";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			try {
				ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
				Assertions.fail("did not throw expected exception");
			} catch (CalculationEngineException e) {
				Assertions.assertEquals(true, e.getMessage().contains("Incompatible types for operation"));
			}
		}
		{
			String expr = "false and 5";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			try {
				ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
				Assertions.fail("did not throw expected exception");
			} catch (CalculationEngineException e) {
				Assertions.assertEquals(true, e.getMessage().contains("Incompatible types for operation"));
			}
		}

		{
			String expr = "true or true";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			boolean result = (boolean) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(true, result);
		}
		{
			String expr = "true or false";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			boolean result = (boolean) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(true, result);
		}
		{
			String expr = "false or true";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			boolean result = (boolean) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(true, result);
		}
		{
			String expr = "false or false";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			boolean result = (boolean) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(false, result);
		}
		{
			String expr = "4 or true";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			try {
				ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
				Assertions.fail("did not throw expected exception");
			} catch (CalculationEngineException e) {
				Assertions.assertEquals(true, e.getMessage().contains("Incompatible types for operation"));
			}
		}
		{
			String expr = "false or 5";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			try {
				ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
				Assertions.fail("did not throw expected exception");
			} catch (CalculationEngineException e) {
				Assertions.assertEquals(true, e.getMessage().contains("Incompatible types for operation"));
			}
		}

		{
			String expr = "true xor true";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			boolean result = (boolean) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(false, result);
		}
		{
			String expr = "true xor false";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			boolean result = (boolean) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(true, result);
		}
		{
			String expr = "false xor true";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			boolean result = (boolean) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(true, result);
		}
		{
			String expr = "false xor false";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			boolean result = (boolean) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(false, result);
		}
		{
			String expr = "4 xor true";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			try {
				ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
				Assertions.fail("did not throw expected exception");
			} catch (CalculationEngineException e) {
				Assertions.assertEquals(true, e.getMessage().contains("Incompatible types for operation"));
			}
		}
		{
			String expr = "false xor 5";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			try {
				ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
				Assertions.fail("did not throw expected exception");
			} catch (CalculationEngineException e) {
				Assertions.assertEquals(true, e.getMessage().contains("Incompatible types for operation"));
			}
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

	@Test
	void test_calculations() throws Exception {
		{
			String expr = "%'weight' > 50";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			boolean result = (boolean) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(false, result);
		}
		{
			String expr = "%'weight' > 0.5";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			boolean result = (boolean) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(true, result);
		}
		{
			String expr = "%'volume' < 1500";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			boolean result = (boolean) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(true, result);
		}
		{
			String expr = "%'volume' < 23";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			boolean result = (boolean) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(false, result);
		}
		{
			String expr = "20 * %'weight'";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			BigDecimal result = (BigDecimal) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(new BigDecimal("20"), result);
		}
		{
			String expr = "0.03 * %'volume'";
			CalculationEngine ce = new CalculationEngine(expr);
			Assertions.assertEquals(0, ce.getErrors().size());
			BigDecimal result = (BigDecimal) ce.evaluate(BigDecimal.ONE, TWO, THREE, FOUR);
			Assertions.assertEquals(new BigDecimal("0.72"), result);
		}
	}
}
