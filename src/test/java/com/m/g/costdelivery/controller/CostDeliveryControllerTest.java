package com.m.g.costdelivery.controller;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.function.Consumer;

import javax.annotation.Resource;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.m.g.costdelivery.config.CommonConfig;
import com.m.g.costdelivery.config.TestSpringConfig;
import com.m.g.costdelivery.controller.model.CalculateCostDeliveryRequest;
import com.m.g.costdelivery.controller.model.CalculateCostDeliveryResponse;
import com.m.g.costdelivery.controller.model.ErrorResponse;
import com.m.g.costdelivery.util.Util;

@ContextConfiguration(classes = { TestSpringConfig.class, CommonConfig.class })
// we define a port here so it can be accessed by the mock voucher controller
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT, properties = {
	"server.port=65123",
})
class CostDeliveryControllerTest {

	private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(CostDeliveryControllerTest.class);

	@LocalServerPort
	private int port;

	@Resource
	private RestTemplate restTemplate;

	private String baseUrl;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		baseUrl = "http://localhost:" + port;
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test_defaultRules_noVoucher() throws Exception {
		// weight exceeds 50kg (Reject)
		{
			String requestUriStr = baseUrl + "/cost_delivery/_calculate";
			String content = null;
			{
				CalculateCostDeliveryRequest request = new CalculateCostDeliveryRequest();
				request.setWeight(new BigDecimal("50.1"));
				request.setHeight(BigDecimal.ONE);
				request.setWidth(BigDecimal.ONE);
				request.setLength(BigDecimal.ONE);
				content = Util.toStr(request);
			}
			LOG.trace("the request: {}", content);
			HttpHeaders hdrs = new HttpHeaders();
			hdrs.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
			hdrs.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
			HttpEntity<String> httpReq = new HttpEntity<String>(content, hdrs);
			try {
				ResponseEntity<String> httpRsp = restTemplate.exchange(requestUriStr, HttpMethod.POST, httpReq, String.class);
				LOG.trace("the response: {}", httpRsp.getBody());
				Assertions.fail("did not throw expected exception");
			} catch (HttpClientErrorException e) {
				Assertions.assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
				ErrorResponse rsp = Util.toObj(e.getResponseBodyAsString(), ErrorResponse.class);
				Assertions.assertEquals(true, rsp.getError().contains("Request cannot be processed due to the rules"));
			}
		}

		// weight exceeds 10kg (Heavy Parcel)
		{
			String requestUriStr = baseUrl + "/cost_delivery/_calculate";
			String content = null;
			{
				CalculateCostDeliveryRequest request = new CalculateCostDeliveryRequest();
				request.setWeight(new BigDecimal("50"));
				request.setHeight(BigDecimal.ONE);
				request.setWidth(BigDecimal.ONE);
				request.setLength(BigDecimal.ONE);
				content = Util.toStr(request);
			}
			LOG.trace("the request: {}", content);
			HttpHeaders hdrs = new HttpHeaders();
			hdrs.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
			hdrs.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
			HttpEntity<String> httpReq = new HttpEntity<String>(content, hdrs);
			ResponseEntity<String> httpRsp = restTemplate.exchange(requestUriStr, HttpMethod.POST, httpReq, String.class);
			LOG.trace("the response: {}", httpRsp.getBody());
			Assertions.assertEquals(HttpStatus.OK, httpRsp.getStatusCode());
			CalculateCostDeliveryResponse rsp = Util.toObj(httpRsp.getBody(), CalculateCostDeliveryResponse.class);
			Assertions.assertEquals(new BigDecimal("1000"), rsp.getCost());
		}

		// volume less than 1500cm3 (Small Parcel)
		{
			String requestUriStr = baseUrl + "/cost_delivery/_calculate";
			String content = null;
			{
				CalculateCostDeliveryRequest request = new CalculateCostDeliveryRequest();
				request.setWeight(new BigDecimal("10"));
				request.setHeight(BigDecimal.ONE);
				request.setWidth(BigDecimal.ONE);
				request.setLength(BigDecimal.ONE);
				content = Util.toStr(request);
			}
			LOG.trace("the request: {}", content);
			HttpHeaders hdrs = new HttpHeaders();
			hdrs.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
			hdrs.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
			HttpEntity<String> httpReq = new HttpEntity<String>(content, hdrs);
			ResponseEntity<String> httpRsp = restTemplate.exchange(requestUriStr, HttpMethod.POST, httpReq, String.class);
			LOG.trace("the response: {}", httpRsp.getBody());
			Assertions.assertEquals(HttpStatus.OK, httpRsp.getStatusCode());
			CalculateCostDeliveryResponse rsp = Util.toObj(httpRsp.getBody(), CalculateCostDeliveryResponse.class);
			Assertions.assertEquals(new BigDecimal("0.03"), rsp.getCost());
		}

		// volume less than 1500cm3 (Small Parcel)
		{
			String requestUriStr = baseUrl + "/cost_delivery/_calculate";
			String content = null;
			{
				CalculateCostDeliveryRequest request = new CalculateCostDeliveryRequest();
				request.setWeight(new BigDecimal("10"));
				request.setHeight(new BigDecimal("2"));
				request.setWidth(new BigDecimal("2"));
				request.setLength(new BigDecimal("374.75"));
				content = Util.toStr(request);
			}
			LOG.trace("the request: {}", content);
			HttpHeaders hdrs = new HttpHeaders();
			hdrs.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
			hdrs.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
			HttpEntity<String> httpReq = new HttpEntity<String>(content, hdrs);
			ResponseEntity<String> httpRsp = restTemplate.exchange(requestUriStr, HttpMethod.POST, httpReq, String.class);
			LOG.trace("the response: {}", httpRsp.getBody());
			Assertions.assertEquals(HttpStatus.OK, httpRsp.getStatusCode());
			CalculateCostDeliveryResponse rsp = Util.toObj(httpRsp.getBody(), CalculateCostDeliveryResponse.class);
			Assertions.assertEquals(new BigDecimal("44.97"), rsp.getCost());
		}

		// volume less than 2500cm3 (Medium Parcel)
		{
			String requestUriStr = baseUrl + "/cost_delivery/_calculate";
			String content = null;
			{
				CalculateCostDeliveryRequest request = new CalculateCostDeliveryRequest();
				request.setWeight(new BigDecimal("10"));
				request.setHeight(new BigDecimal("2"));
				request.setWidth(new BigDecimal("2"));
				request.setLength(new BigDecimal("375"));
				content = Util.toStr(request);
			}
			LOG.trace("the request: {}", content);
			HttpHeaders hdrs = new HttpHeaders();
			hdrs.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
			hdrs.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
			HttpEntity<String> httpReq = new HttpEntity<String>(content, hdrs);
			ResponseEntity<String> httpRsp = restTemplate.exchange(requestUriStr, HttpMethod.POST, httpReq, String.class);
			LOG.trace("the response: {}", httpRsp.getBody());
			Assertions.assertEquals(HttpStatus.OK, httpRsp.getStatusCode());
			CalculateCostDeliveryResponse rsp = Util.toObj(httpRsp.getBody(), CalculateCostDeliveryResponse.class);
			Assertions.assertEquals(new BigDecimal("60"), rsp.getCost());
		}

		// volume less than 2500cm3 (Medium Parcel)
		{
			String requestUriStr = baseUrl + "/cost_delivery/_calculate";
			String content = null;
			{
				CalculateCostDeliveryRequest request = new CalculateCostDeliveryRequest();
				request.setWeight(new BigDecimal("10"));
				request.setHeight(new BigDecimal("2"));
				request.setWidth(new BigDecimal("2"));
				request.setLength(new BigDecimal("624.75"));
				content = Util.toStr(request);
			}
			LOG.trace("the request: {}", content);
			HttpHeaders hdrs = new HttpHeaders();
			hdrs.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
			hdrs.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
			HttpEntity<String> httpReq = new HttpEntity<String>(content, hdrs);
			ResponseEntity<String> httpRsp = restTemplate.exchange(requestUriStr, HttpMethod.POST, httpReq, String.class);
			LOG.trace("the response: {}", httpRsp.getBody());
			Assertions.assertEquals(HttpStatus.OK, httpRsp.getStatusCode());
			CalculateCostDeliveryResponse rsp = Util.toObj(httpRsp.getBody(), CalculateCostDeliveryResponse.class);
			Assertions.assertEquals(new BigDecimal("99.96"), rsp.getCost());
		}

		// volume greater than or equal 2500cm3 (Large Parcel)
		{
			String requestUriStr = baseUrl + "/cost_delivery/_calculate";
			String content = null;
			{
				CalculateCostDeliveryRequest request = new CalculateCostDeliveryRequest();
				request.setWeight(new BigDecimal("10"));
				request.setHeight(new BigDecimal("2"));
				request.setWidth(new BigDecimal("2"));
				request.setLength(new BigDecimal("625"));
				content = Util.toStr(request);
			}
			LOG.trace("the request: {}", content);
			HttpHeaders hdrs = new HttpHeaders();
			hdrs.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
			hdrs.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
			HttpEntity<String> httpReq = new HttpEntity<String>(content, hdrs);
			ResponseEntity<String> httpRsp = restTemplate.exchange(requestUriStr, HttpMethod.POST, httpReq, String.class);
			LOG.trace("the response: {}", httpRsp.getBody());
			Assertions.assertEquals(HttpStatus.OK, httpRsp.getStatusCode());
			CalculateCostDeliveryResponse rsp = Util.toObj(httpRsp.getBody(), CalculateCostDeliveryResponse.class);
			Assertions.assertEquals(new BigDecimal("125"), rsp.getCost());
		}
	}

	@Test
	void test_defaultRules_withVoucher() throws Exception {
		// no voucher
		{
			String requestUriStr = baseUrl + "/cost_delivery/_calculate";
			String content = null;
			{
				CalculateCostDeliveryRequest request = new CalculateCostDeliveryRequest();
				request.setWeight(new BigDecimal("10"));
				request.setHeight(new BigDecimal("2"));
				request.setWidth(new BigDecimal("2"));
				request.setLength(new BigDecimal("625"));
				content = Util.toStr(request);
			}
			LOG.trace("the request: {}", content);
			HttpHeaders hdrs = new HttpHeaders();
			hdrs.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
			hdrs.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
			HttpEntity<String> httpReq = new HttpEntity<String>(content, hdrs);
			ResponseEntity<String> httpRsp = restTemplate.exchange(requestUriStr, HttpMethod.POST, httpReq, String.class);
			LOG.trace("the response: {}", httpRsp.getBody());
			Assertions.assertEquals(HttpStatus.OK, httpRsp.getStatusCode());
			CalculateCostDeliveryResponse rsp = Util.toObj(httpRsp.getBody(), CalculateCostDeliveryResponse.class);
			Assertions.assertEquals(new BigDecimal("125"), rsp.getCost());
		}

		// SENIOR voucher
		{
			String requestUriStr = baseUrl + "/cost_delivery/_calculate";
			String content = null;
			{
				CalculateCostDeliveryRequest request = new CalculateCostDeliveryRequest();
				request.setWeight(new BigDecimal("10"));
				request.setHeight(new BigDecimal("2"));
				request.setWidth(new BigDecimal("2"));
				request.setLength(new BigDecimal("625"));
				request.setVoucherCode("SENIOR");
				content = Util.toStr(request);
			}
			LOG.trace("the request: {}", content);
			HttpHeaders hdrs = new HttpHeaders();
			hdrs.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
			hdrs.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
			HttpEntity<String> httpReq = new HttpEntity<String>(content, hdrs);
			ResponseEntity<String> httpRsp = restTemplate.exchange(requestUriStr, HttpMethod.POST, httpReq, String.class);
			LOG.trace("the response: {}", httpRsp.getBody());
			Assertions.assertEquals(HttpStatus.OK, httpRsp.getStatusCode());
			CalculateCostDeliveryResponse rsp = Util.toObj(httpRsp.getBody(), CalculateCostDeliveryResponse.class);
			Assertions.assertEquals(new BigDecimal("100"), rsp.getCost());
		}

		// COUPON voucher
		{
			String requestUriStr = baseUrl + "/cost_delivery/_calculate";
			String content = null;
			{
				CalculateCostDeliveryRequest request = new CalculateCostDeliveryRequest();
				request.setWeight(new BigDecimal("10"));
				request.setHeight(new BigDecimal("2"));
				request.setWidth(new BigDecimal("2"));
				request.setLength(new BigDecimal("625"));
				request.setVoucherCode("COUPON");
				content = Util.toStr(request);
			}
			LOG.trace("the request: {}", content);
			HttpHeaders hdrs = new HttpHeaders();
			hdrs.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
			hdrs.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
			HttpEntity<String> httpReq = new HttpEntity<String>(content, hdrs);
			ResponseEntity<String> httpRsp = restTemplate.exchange(requestUriStr, HttpMethod.POST, httpReq, String.class);
			LOG.trace("the response: {}", httpRsp.getBody());
			Assertions.assertEquals(HttpStatus.OK, httpRsp.getStatusCode());
			CalculateCostDeliveryResponse rsp = Util.toObj(httpRsp.getBody(), CalculateCostDeliveryResponse.class);
			Assertions.assertEquals(new BigDecimal("118.75"), rsp.getCost());
		}

		// EXPIRED voucher
		{
			String requestUriStr = baseUrl + "/cost_delivery/_calculate";
			String content = null;
			{
				CalculateCostDeliveryRequest request = new CalculateCostDeliveryRequest();
				request.setWeight(new BigDecimal("10"));
				request.setHeight(new BigDecimal("2"));
				request.setWidth(new BigDecimal("2"));
				request.setLength(new BigDecimal("625"));
				request.setVoucherCode("EXPIRED");
				content = Util.toStr(request);
			}
			LOG.trace("the request: {}", content);
			HttpHeaders hdrs = new HttpHeaders();
			hdrs.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
			hdrs.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
			HttpEntity<String> httpReq = new HttpEntity<String>(content, hdrs);
			try {
				ResponseEntity<String> httpRsp = restTemplate.exchange(requestUriStr, HttpMethod.POST, httpReq, String.class);
				LOG.trace("the response: {}", httpRsp.getBody());
				Assertions.fail("did not throw expected exception");
			} catch (HttpClientErrorException e) {
				Assertions.assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
				ErrorResponse rsp = Util.toObj(e.getResponseBodyAsString(), ErrorResponse.class);
				Assertions.assertEquals(true, rsp.getError().contains("The provided voucher code is expired"));
			}
		}

		// invalid voucher
		{
			String requestUriStr = baseUrl + "/cost_delivery/_calculate";
			String content = null;
			{
				CalculateCostDeliveryRequest request = new CalculateCostDeliveryRequest();
				request.setWeight(new BigDecimal("10"));
				request.setHeight(new BigDecimal("2"));
				request.setWidth(new BigDecimal("2"));
				request.setLength(new BigDecimal("625"));
				request.setVoucherCode(UUID.randomUUID().toString());
				content = Util.toStr(request);
			}
			LOG.trace("the request: {}", content);
			HttpHeaders hdrs = new HttpHeaders();
			hdrs.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
			hdrs.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
			HttpEntity<String> httpReq = new HttpEntity<String>(content, hdrs);
			try {
				ResponseEntity<String> httpRsp = restTemplate.exchange(requestUriStr, HttpMethod.POST, httpReq, String.class);
				LOG.trace("the response: {}", httpRsp.getBody());
				Assertions.fail("did not throw expected exception");
			} catch (HttpServerErrorException e) {
				Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, e.getStatusCode());
				ErrorResponse rsp = Util.toObj(e.getResponseBodyAsString(), ErrorResponse.class);
				Assertions.assertEquals(true, rsp.getError().contains("Invalid voucher code"));
			}
		}
	}

	@Test
	void test_defaultRules_paramValidation() throws Exception {
		doCalculateCostParamValidation(r -> r.setWeight(null), "'weight' must not be null");
		doCalculateCostParamValidation(r -> r.setWeight(new BigDecimal("-1")), "'weight' must be greater than zero");
		doCalculateCostParamValidation(r -> r.setWeight(BigDecimal.ZERO), "'weight' must be greater than zero");

		doCalculateCostParamValidation(r -> r.setHeight(null), "'height' must not be null");
		doCalculateCostParamValidation(r -> r.setHeight(new BigDecimal("-1")), "'height' must be greater than zero");
		doCalculateCostParamValidation(r -> r.setHeight(BigDecimal.ZERO), "'height' must be greater than zero");

		doCalculateCostParamValidation(r -> r.setWidth(null), "'width' must not be null");
		doCalculateCostParamValidation(r -> r.setWidth(new BigDecimal("-1")), "'width' must be greater than zero");
		doCalculateCostParamValidation(r -> r.setWidth(BigDecimal.ZERO), "'width' must be greater than zero");

		doCalculateCostParamValidation(r -> r.setLength(null), "'length' must not be null");
		doCalculateCostParamValidation(r -> r.setLength(new BigDecimal("-1")), "'length' must be greater than zero");
		doCalculateCostParamValidation(r -> r.setLength(BigDecimal.ZERO), "'length' must be greater than zero");
	}

	private void doCalculateCostParamValidation(Consumer<CalculateCostDeliveryRequest> consumer, String expectedMsg) throws Exception {
		String requestUriStr = baseUrl + "/cost_delivery/_calculate";
		String content = null;
		{
			CalculateCostDeliveryRequest request = new CalculateCostDeliveryRequest();
			request.setWeight(new BigDecimal("50.1"));
			request.setHeight(BigDecimal.ONE);
			request.setWidth(BigDecimal.ONE);
			request.setLength(BigDecimal.ONE);
			consumer.accept(request);
			content = Util.toStr(request);
		}
		LOG.trace("the request: {}", content);
		HttpHeaders hdrs = new HttpHeaders();
		hdrs.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		hdrs.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<String> httpReq = new HttpEntity<String>(content, hdrs);
		try {
			ResponseEntity<String> httpRsp = restTemplate.exchange(requestUriStr, HttpMethod.POST, httpReq, String.class);
			LOG.trace("the response: {}", httpRsp.getBody());
			Assertions.fail("did not throw expected exception");
		} catch (HttpClientErrorException e) {
			Assertions.assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
			ErrorResponse rsp = Util.toObj(e.getResponseBodyAsString(), ErrorResponse.class);
			Assertions.assertEquals(true, rsp.getError().contains(expectedMsg));
		}
	}
}
