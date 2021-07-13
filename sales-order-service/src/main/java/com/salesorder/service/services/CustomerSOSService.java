package com.salesorder.service.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.salesorder.service.entity.CustomerSOS;
import com.salesorder.service.pojo.Response;
import com.salesorder.service.repository.CustomerSOSRepository;

@Component
public class CustomerSOSService {

	private Logger logger = LoggerFactory.getLogger(CustomerSOSService.class);

	@Autowired
	private CustomerSOSRepository customerSOSRepsoitory;

	public Response createCustomer(@RequestBody CustomerSOS customerSOS) {
		logger.info("createCustomer() started ");
		Response response = new Response();
		CustomerSOS customer = customerSOSRepsoitory.save(customerSOS);
		response.setResult(customer);
		return response;
	}

	public Response getCustomers() {
		logger.info("getCustomers() started ");
		Response response = new Response();
		List<CustomerSOS> customers = customerSOSRepsoitory.findAll();
		response.setResult(customers);
		return response;
	}
	
	public Response getCustomersById(int id) {
		logger.info("getCustomersById() started ");
		Response response = new Response();
		Optional<CustomerSOS> customer = customerSOSRepsoitory.findById(id);
		response.setResult(customer);
		return response;
	}

}
