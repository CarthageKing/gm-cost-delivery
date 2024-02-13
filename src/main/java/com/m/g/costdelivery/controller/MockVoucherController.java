package com.m.g.costdelivery.controller;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.m.g.costdelivery.controller.model.VoucherItem;
import com.m.g.costdelivery.exception.BadRequestException;
import com.m.g.costdelivery.util.AppConstants;

@RestController
public class MockVoucherController {

	public MockVoucherController() {
		// noop
	}

	@GetMapping(value = AppConstants.MOCK_VOUCHER_API_CONTEXT_PATH + "/{voucherCode}", produces = MediaType.APPLICATION_JSON_VALUE)
	public VoucherItem createSpeech(@PathVariable String voucherCode) {
		VoucherItem vi = new VoucherItem();
		vi.setCode(voucherCode);
		if ("SENIOR".equals(voucherCode)) {
			// always valid
			vi.setDiscount(new BigDecimal("0.2"));
			vi.setExpiry(LocalDate.now().plusDays(1));
		} else if ("COUPON".equals(voucherCode)) {
			// always valid
			vi.setDiscount(new BigDecimal("0.05"));
			vi.setExpiry(LocalDate.now().plusDays(1));
		} else if ("EXPIRED".equals(voucherCode)) {
			// invalid
			vi.setDiscount(BigDecimal.ONE);
			vi.setExpiry(LocalDate.now().minusDays(1));
		} else {
			throw new BadRequestException("Invalid voucher code");
		}
		return vi;
	}
}
