package com.cognizant.project.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.project.dto.PackagingAndDeliveryDTO;

@FeignClient(name = "packaging-delivery-client",url = "http://localhost:8002")
public interface PackagingAndDeliveryClient {
	
	@PostMapping("/getPackagingDeliveryCharge/{type}/{count}")
	public PackagingAndDeliveryDTO packagingAndDeliveryDTO(@PathVariable String type, @PathVariable int count,
			@RequestHeader(name = "Authorization", required = true) String token);

}
