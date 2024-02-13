package com.m.g.costdelivery.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.m.g.costdelivery.calc_engine.CalculationEngine;
import com.m.g.costdelivery.calc_engine.dao.CostDeliveryRuleDao;
import com.m.g.costdelivery.calc_engine.dao.CostDeliveryRuleEntity;
import com.m.g.costdelivery.controller.model.CalculateCostDeliveryRequest;
import com.m.g.costdelivery.controller.model.CalculateCostDeliveryResponse;
import com.m.g.costdelivery.controller.model.VoucherItem;
import com.m.g.costdelivery.exception.BadRequestException;
import com.m.g.costdelivery.exception.CostDeliveryException;
import com.m.g.costdelivery.util.AppConstants;
import com.m.g.costdelivery.util.Util;

@Service
public class CostDeliveryService {

	@Resource
	private CostDeliveryRuleDao cdRuleDao;

	@Resource
	private VoucherApi voucherApi;

	public CostDeliveryService() {
		// noop
	}

	public CalculateCostDeliveryResponse calculateCost(CalculateCostDeliveryRequest request) {
		validateNotNullNotZeroPositiveValue(request.getWeight(), "weight");
		validateNotNullNotZeroPositiveValue(request.getHeight(), "height");
		validateNotNullNotZeroPositiveValue(request.getWidth(), "width");
		validateNotNullNotZeroPositiveValue(request.getLength(), "length");
		List<CostDeliveryRuleEntity> rules = cdRuleDao.findAllOrderByPriority();
		if (CollectionUtils.isEmpty(rules)) {
			throw new CostDeliveryException("No rules are configured! Please configure rules first");
		}
		for (CostDeliveryRuleEntity rule : rules) {
			String cond = StringUtils.trimToEmpty(rule.getCondition());
			boolean doEvalCost = false;
			if (cond.length() < 1) {
				doEvalCost = true;
			} else {
				String expr = cond.substring(AppConstants.RULE_MARKER_EXPR.length());
				CalculationEngine ce = new CalculationEngine(expr);
				doEvalCost = (boolean) ce.evaluate(request.getWeight(), request.getHeight(), request.getWidth(), request.getLength());
			}

			if (doEvalCost) {
				String cost = StringUtils.trimToEmpty(rule.getCost());
				if (cost.startsWith(AppConstants.RULE_MARKER_ERR)) {
					String val = cost.substring(AppConstants.RULE_MARKER_ERR.length());
					if (AppConstants.RULE_ERR_REJECT.equals(val)) {
						throw new BadRequestException("Request cannot be processed due to the rules. Please review the rules");
					} else {
						// shouldn't happen
						throw new IllegalStateException("Malformed cost expression: " + cost);
					}
				} else if (cost.startsWith(AppConstants.RULE_MARKER_EXPR)) {
					String expr = cost.substring(AppConstants.RULE_MARKER_EXPR.length());
					CalculationEngine ce = new CalculationEngine(expr);
					BigDecimal result = (BigDecimal) ce.evaluate(request.getWeight(), request.getHeight(), request.getWidth(), request.getLength());

					String voucherCode = StringUtils.trimToEmpty(request.getVoucherCode());
					if (voucherCode.length() > 0) {
						VoucherItem vi = voucherApi.getVoucherDetails(voucherCode);
						if (null != vi.getExpiry()) {
							if (LocalDate.now().compareTo(vi.getExpiry()) <= 0) {
								// voucher still valid
								BigDecimal discountPct = vi.getDiscount();
								BigDecimal discount = result.multiply(discountPct);
								result = Util.stripTrailingZerosAfterDecimalPoint(result.subtract(discount));
							} else {
								// expired voucher
								throw new BadRequestException("The provided voucher code is expired");
							}
						}
					}

					CalculateCostDeliveryResponse response = new CalculateCostDeliveryResponse();
					response.setCost(result);
					return response;
				} else {
					// shouldn't happen
					throw new IllegalStateException("Malformed cost expression: " + cost);
				}
			}
		}
		throw new CostDeliveryException("Request cannot be processed since it matched no rules. Please review the rules");
	}

	private void validateNotNullNotZeroPositiveValue(BigDecimal val, String varName) {
		if (null == val) {
			throw new BadRequestException("'" + varName + "' must not be null");
		}
		if (val.compareTo(BigDecimal.ZERO) <= 0) {
			throw new BadRequestException("'" + varName + "' must be greater than zero");
		}
	}
}
