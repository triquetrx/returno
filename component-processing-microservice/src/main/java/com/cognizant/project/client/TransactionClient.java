package com.cognizant.project.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.project.dto.CreditCardDto;

@FeignClient(name = "transaction-api",url = "http://localhost:8003")
public interface TransactionClient {

	@PostMapping("/processpayment/{requestId}")
	public ResponseEntity<?> processPayment(@RequestHeader(name = "Authorization", required = true) String token,
			@RequestBody CreditCardDto cardDto,@PathVariable int requestId) throws Exception;
	
}
