package com.m.g.costdelivery.calc_engine.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.m.g.costdelivery.util.AppConstants;

// doc didn't say to implement a db, just that the "rules need to be as flexible as possible".
// simulate the dao so in the future if db is needed, there is not much change in the code except for this part
@Repository
public class CostDeliveryRuleDao {

	private List<CostDeliveryRuleEntity> rules = new ArrayList<>();

	public CostDeliveryRuleDao() {
		// put the default rules
		rules.addAll(Arrays.asList(
			CostDeliveryRuleEntity.create(1, "Reject", AppConstants.RULE_MARKER_EXPR + "%'weight' > 50", AppConstants.RULE_MARKER_ERR + AppConstants.RULE_ERR_REJECT),
			CostDeliveryRuleEntity.create(2, "Heavy Parcel", AppConstants.RULE_MARKER_EXPR + "%'weight' > 10", AppConstants.RULE_MARKER_EXPR + "20 * %'weight'"),
			CostDeliveryRuleEntity.create(3, "Small Parcel", AppConstants.RULE_MARKER_EXPR + "%'volume' < 1500", AppConstants.RULE_MARKER_EXPR + "0.03 * %'volume'"),
			CostDeliveryRuleEntity.create(4, "Medium Parcel", AppConstants.RULE_MARKER_EXPR + "%'volume' < 2500", AppConstants.RULE_MARKER_EXPR + "0.04 * %'volume'"),
			CostDeliveryRuleEntity.create(5, "Large Parcel", null, AppConstants.RULE_MARKER_EXPR + "0.05 * %'volume'")));

		// perform validation
		for (CostDeliveryRuleEntity e : rules) {
			CostDeliveryRuleEntity.validate(e);
		}
	}

	public List<CostDeliveryRuleEntity> findAllOrderByPriority() {
		return new ArrayList<>(rules);
	}
}
