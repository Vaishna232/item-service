package com.salesorder.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.salesorder.service.entity.SalesOrder;
import com.salesorder.service.pojo.Response;
import com.salesorder.service.services.SalesOrderService;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
public class SalesOrderController {

	private Logger logger = LoggerFactory.getLogger(SalesOrderController.class);
	
	@Autowired
	SalesOrderService salesOrderService;
	
	@Autowired
	private Environment env;

	@PostMapping("/orders")
	@CircuitBreaker(name="default", fallbackMethod="fallbackMethod")
	@RateLimiter(name="default")
	@Bulkhead(name="default")
	public Response createOrder(@RequestBody SalesOrder order) throws Exception {
		logger.info("createOrder() started ");
		String port = env.getProperty("local.server.port");
		Response response = salesOrderService.createOrder(order);
		response.setPort(port);
		return response;
	}
	
	@GetMapping("/showorders")
	@CircuitBreaker(name="default", fallbackMethod="fallbackMethod")
	@RateLimiter(name="default")
	@Bulkhead(name="default")
	public Response showorders() {
		logger.info("showorders() started ");
		String port = env.getProperty("local.server.port");
		Response response = salesOrderService.showorders();
		response.setPort(port);
		return response;
	}

	public Response fallbackMethod(Exception e) {
		Response response = new Response();
		response.setStatus("FAILURE");
		response.setErrorcode("-1");
		response.setErrormessage(e.getMessage());
		return response;
	}

	
}
