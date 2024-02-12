package com.m.g.costdelivery.controller;

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
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.m.g.costdelivery.config.CommonConfig;
import com.m.g.costdelivery.config.TestSpringConfig;

@ContextConfiguration(classes = { TestSpringConfig.class, CommonConfig.class })
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CostDeliveryControllerTest {

	private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(CostDeliveryControllerTest.class);

	private static final ObjectMapper OBJECT_MAPPER;

	static {
		OBJECT_MAPPER = new ObjectMapper().registerModule(new JavaTimeModule());
	}

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
	void test_allApis() {
		// dummy test
		{
			String requestUriStr = baseUrl + "/cost_delivery/_calculate";
			String content = null;
			LOG.trace("the request: {}", content);
			HttpHeaders hdrs = new HttpHeaders();
			hdrs.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
			hdrs.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
			HttpEntity<String> httpReq = new HttpEntity<String>(content, hdrs);
			ResponseEntity<String> httpRsp = restTemplate.exchange(requestUriStr, HttpMethod.GET, httpReq, String.class);
			LOG.trace("the response: {}", httpRsp.getBody());
			Assertions.assertEquals(HttpStatus.OK, httpRsp.getStatusCode());
		}
	}
}
