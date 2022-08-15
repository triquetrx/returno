package com.cognizant.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.project.client.AuthClient;
import com.cognizant.project.dto.CreditCardDto;
import com.cognizant.project.exception.InvalidCredentials;
import com.cognizant.project.service.PaymentService;

@RestController
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	@Autowired
	private AuthClient authClient;

	@CrossOrigin
	@PostMapping("/processpayment/{requestId}")
	public ResponseEntity<?> processPayment(@RequestHeader(name = "Authorization", required = true) String token,
			@RequestBody CreditCardDto cardDto,@PathVariable int requestId) throws Exception {

		try {
			if (!authClient.validatingDTO(token).isValidStatus()) {
				throw new InvalidCredentials("Invalid Credentials");
			}
		} catch (Exception e) {
			throw new Exception("Invalid Token");
		}
		if (paymentService.isCreditCardValid(cardDto)) {
			return ResponseEntity.ok(paymentService.getBalance(cardDto,requestId));
		}
		return new ResponseEntity<>(new Exception("Invalid Credit Card Detail"), HttpStatus.UNAUTHORIZED);
	}
	
}
