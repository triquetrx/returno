package com.cognizant.reactemployee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.reactemployee.client.AuthClient;
import com.cognizant.reactemployee.dto.PackagingAndDeliveryDTO;
import com.cognizant.reactemployee.exceptions.ComponentTypeNotFoundException;
import com.cognizant.reactemployee.exceptions.InvalidTokenException;
import com.cognizant.reactemployee.service.PackagingAndDeliveryService;

import feign.FeignException;

@RestController
public class PackagingController {

	@Autowired
	private PackagingAndDeliveryService packagingAndDeliveryService;
	@Autowired
	private AuthClient authClient;

	@CrossOrigin
	@PostMapping("/getPackagingDeliveryCharge/{type}/{count}")
	public ResponseEntity<PackagingAndDeliveryDTO> calculatingPackagingAndDeliveryCharge(@PathVariable String type, @PathVariable int count,
			@RequestHeader(name = "Authorization", required = true) String token)
			throws InvalidTokenException, ComponentTypeNotFoundException {

		try {
			if (!authClient.validating(token).isValidStatus()) {
				
				throw new InvalidTokenException("Token is either expired or invalid...");
			}

		} catch (FeignException e) {
			throw new InvalidTokenException("Token is either expired or invalid...");

		}

		try {
			return new ResponseEntity<>(packagingAndDeliveryService.calculatePackagingAndDeliveryCharge(type, count),
					HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(packagingAndDeliveryService.calculatePackagingAndDeliveryCharge(type, count),
					HttpStatus.FORBIDDEN);
		}
	}
	
}
