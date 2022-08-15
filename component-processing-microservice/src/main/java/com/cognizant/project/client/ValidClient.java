package com.cognizant.project.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.project.dto.ValidDto;

@FeignClient(name = "authorization",url = "http://localhost:8001")
public interface ValidClient {
	
	@GetMapping("/validate")
	public ValidDto validDto(@RequestHeader(name = "Authorization",required = true) String token);

}
