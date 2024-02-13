package com.m.g.costdelivery.service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.m.g.costdelivery.controller.model.ErrorResponse;
import com.m.g.costdelivery.controller.model.VoucherItem;
import com.m.g.costdelivery.exception.CostDeliveryException;
import com.m.g.costdelivery.util.AppConstants;
import com.m.g.costdelivery.util.Util;

@Service
public class VoucherApi {

	private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(VoucherApi.class);

	// FIXME: This needs to be in some secret manager
	private static final String APIKEY = "apikey";

	@Resource
	private RestTemplate restTemplate;

	@Value("${app.extapi.get_voucher_api_url}")
	private String getVoucherApiUrl;

	@Value("${server.port}")
	private int localPort;

	public VoucherApi() {
		// noop
	}

	@PostConstruct
	public void afterPropertiesSet() throws Exception {
		getVoucherApiUrl = StringUtils.trimToEmpty(getVoucherApiUrl);
		if ("MOCK".equals(getVoucherApiUrl)) {
			getVoucherApiUrl = "http://localhost:" + localPort + AppConstants.MOCK_VOUCHER_API_CONTEXT_PATH;
		}
	}

	public VoucherItem getVoucherDetails(String voucherCode) {
		String requestUriStr = getVoucherApiUrl + "/" + voucherCode + "?key=" + APIKEY;
		String content = null;
		LOG.trace("the request: {}", content);
		HttpHeaders hdrs = new HttpHeaders();
		hdrs.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		hdrs.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<String> httpReq = new HttpEntity<>(content, hdrs);
		try {
			ResponseEntity<String> httpRsp = restTemplate.exchange(requestUriStr, HttpMethod.GET, httpReq, String.class);
			LOG.trace("the response: {}", httpRsp.getBody());
			return Util.toObj(httpRsp.getBody(), VoucherItem.class);
		} catch (HttpStatusCodeException e) {
			ErrorResponse rsp = Util.toObj(e.getResponseBodyAsString(), ErrorResponse.class);
			throw new CostDeliveryException("A HTTP " + e.getRawStatusCode() + " error occurred while calling a third party API. The error was: " + rsp.getError(), e);
		}
	}
}
