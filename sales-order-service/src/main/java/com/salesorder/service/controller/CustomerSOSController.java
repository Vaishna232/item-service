package com.salesorder.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.salesorder.service.entity.CustomerSOS;
import com.salesorder.service.pojo.Response;
import com.salesorder.service.services.CustomerSOSService;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
public class CustomerSOSController {
	
	private Logger logger = LoggerFactory.getLogger(CustomerSOSController.class);
	
	@Autowired
	CustomerSOSService customerSOSService;
	
	@Autowired
	private Environment env;
	
	@PostMapping("/createSOS")
	@CircuitBreaker(name="default", fallbackMethod="fallbackMethod")
	@RateLimiter(name="default")
	@Bulkhead(name="default")
	public Response createCustomer(@RequestBody CustomerSOS customerSOS) {
		logger.info("createCustomer() started ");
		String port = env.getProperty("local.server.port");
		Response response =   customerSOSService.createCustomer(customerSOS);
		response.setPort(port);
		return response;
	}
	
	@GetMapping("/customerSOS")
	@CircuitBreaker(name="default", fallbackMethod="fallbackMethod")
	@RateLimiter(name="default")
	@Bulkhead(name="default")
	public Response getCustomers() {
		logger.info("getCustomers() started ");
		String port = env.getProperty("local.server.port");
		Response response =   customerSOSService.getCustomers();
		response.setPort(port);
		return response;
	}
	
	@GetMapping("/customerSOS/{id}")
	@CircuitBreaker(name="default", fallbackMethod="fallbackMethod")
	@RateLimiter(name="default")
	@Bulkhead(name="default")
	public Response getCustomers(@PathVariable int id) {
		logger.info("getCustomers() started ");
		String port = env.getProperty("local.server.port");
		Response response =   customerSOSService.getCustomersById(id);
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
