package com.m.g.costdelivery.calc_engine.dao;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import com.m.g.costdelivery.calc_engine.CalculationEngine;
import com.m.g.costdelivery.exception.CostDeliveryException;
import com.m.g.costdelivery.util.AppConstants;

public class CostDeliveryRuleEntity {

	private int priority;
	private String ruleName;
	private String condition;
	private String cost;

	public CostDeliveryRuleEntity() {
		// noop
	}

	public static CostDeliveryRuleEntity create(int priority, String ruleName, String condition, String cost) {
		CostDeliveryRuleEntity cdre = new CostDeliveryRuleEntity();
		cdre.setPriority(priority);
		cdre.setRuleName(ruleName);
		cdre.setCondition(condition);
		cdre.setCost(cost);
		return cdre;
	}

	public static void validate(CostDeliveryRuleEntity e) {
		validateCondition(e.getCondition());
		validateCost(e.getCost());
	}

	private static void validateCost(String str) {
		String txt = StringUtils.trimToEmpty(str);
		if (txt.length() < 1) {
			throw new CostDeliveryException("Malformed cost delivery rule cost. It cannot be empty");
		}
		if (txt.startsWith(AppConstants.RULE_MARKER_EXPR)) {
			String expr = txt.substring(AppConstants.RULE_MARKER_EXPR.length());
			CalculationEngine ce = new CalculationEngine(expr);
			if (!ce.getErrors().isEmpty()) {
				throw new CostDeliveryException("Parsing error on cost delivery rule cost [" + expr + "]. Errors are: " + ce.getErrors());
			}
			// quick sanity test eval of the expression
			try {
				Object result = ce.evaluate(BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE);
				// result must be a number
				if (result instanceof BigDecimal) {
					// do nothing
				} else {
					throw new CostDeliveryException("Cost delivery rule cost [" + expr + "] will not result in a number. Must result in a number");
				}
			} catch (Exception e) {
				throw new CostDeliveryException("Cost delivery rule cost [" + expr + "] sanity check had errors", e);
			}
		} else if (txt.startsWith(AppConstants.RULE_MARKER_ERR)) {
			String val = txt.substring(AppConstants.RULE_MARKER_ERR.length());
			if (AppConstants.RULE_ERR_REJECT.equals(val)) {
				// do nothing
			} else {
				throw new CostDeliveryException("Malformed cost delivery rule cost invalid or unsupported error action");
			}
		} else {
			throw new CostDeliveryException("Malformed cost delivery rule cost: " + str);
		}
	}

	private static void validateCondition(String str) {
		String txt = StringUtils.trimToEmpty(str);
		if (txt.length() < 1) {
			return;
		}
		if (txt.startsWith(AppConstants.RULE_MARKER_EXPR)) {
			String expr = txt.substring(AppConstants.RULE_MARKER_EXPR.length());
			CalculationEngine ce = new CalculationEngine(expr);
			if (!ce.getErrors().isEmpty()) {
				throw new CostDeliveryException("Parsing error on cost delivery rule condition [" + expr + "]. Errors are: " + ce.getErrors());
			}
			// quick sanity test eval of the expression
			try {
				Object result = ce.evaluate(BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE);
				// result must be a boolean
				if (result instanceof Boolean) {
					// do nothing
				} else {
					throw new CostDeliveryException("Cost delivery rule condition [" + expr + "] will not result in a boolean. Must result in a boolean");
				}
			} catch (Exception e) {
				throw new CostDeliveryException("Cost delivery rule condition [" + expr + "] sanity check had errors", e);
			}
		} else {
			throw new CostDeliveryException("Malformed cost delivery rule condition: " + str);
		}
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}
}
