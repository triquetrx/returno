package com.cognizant.project.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.project.dto.ValidatingDTO;

@FeignClient(name = "auth-client",url="http://localhost:8001/")
public interface AuthClient {
	
	@GetMapping("/validate")
	public ValidatingDTO validatingDTO(@RequestHeader(name = "Authorization",required = true)String token);

}
