package com.salesorder.service.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.item.service.entity.Item;

@FeignClient(name="item-service")
public interface ItemServiceProxy {
	
	@GetMapping("/item/{itemname}")
	public Item getbyName(@PathVariable String itemname);

}
