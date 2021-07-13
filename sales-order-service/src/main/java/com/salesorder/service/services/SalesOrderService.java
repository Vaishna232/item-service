package com.salesorder.service.services;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.item.service.entity.Item;
import com.salesorder.service.entity.CustomerSOS;
import com.salesorder.service.entity.OrderLineItem;
import com.salesorder.service.entity.SalesOrder;
import com.salesorder.service.pojo.Response;
import com.salesorder.service.proxy.ItemServiceProxy;
import com.salesorder.service.repository.CustomerSOSRepository;
import com.salesorder.service.repository.SalesOrderRepository;

import io.vavr.control.Option;

@Component
public class SalesOrderService {

	private Logger logger = LoggerFactory.getLogger(SalesOrderService.class);

	@Autowired
	private SalesOrderRepository salesOrderRepository;

	@Autowired
	private CustomerSOSRepository customerSOSRepository;

	@Autowired
	private ItemServiceProxy itemServiceProxy;

	public Response createOrder(SalesOrder order) throws Exception {

		logger.info("createOrder() started ");
		double totalprice = 0;
		Response response = new Response();
		
		Optional<CustomerSOS> customerSOS = customerSOSRepository.findById(order.getCustId());
		if (!customerSOS.isPresent()) {
			throw new Exception("Customer id not found " + order.getCustId());
		}

		for (OrderLineItem ol : order.getOrderLineItem()) {
			Item item = itemServiceProxy.getbyName(ol.getItemName());
		//	if ("SUCCESS".equalsIgnoreCase(resp.getStatus())) {
			//	@SuppressWarnings("unchecked")
		//		LinkedHashMap<String,String> item = (LinkedHashMap<String,String>) resp.getResult();
				if (item == null || !(item.getName().equalsIgnoreCase(ol.getItemName()))) {
					throw new Exception("Item not found " + ol.getItemName());
				}
				//System.out.println("price="+item.get("id").toString());
				Double price = Double.valueOf(item.getPrice());
				price = price * ol.getItemQuantity();
				totalprice += price;
		//	}
		}
		order.setTotalPrice(totalprice);
		SalesOrder so = salesOrderRepository.save(order);
		response.setResult(so);
		return response;

	}

	public Response showorders() {
		logger.info("showorders() started ");
		Response response = new Response();
		response.setResult(salesOrderRepository.findAll());
		return response;
	}

}
