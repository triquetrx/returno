package com.cognizant.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.project.client.ValidClient;
import com.cognizant.project.dto.CreditCardDto;
import com.cognizant.project.dto.ProcessRequestDTO;
import com.cognizant.project.exception.InvalidClientException;
import com.cognizant.project.exception.PriorityException;
import com.cognizant.project.service.ProcessRequestService;
import com.cognizant.project.service.TrackingService;

@RestController
public class ComponentController {

	@Autowired
	ProcessRequestService service;
	@Autowired
	ValidClient validClient;
	@Autowired
	TrackingService trackingService;

	@CrossOrigin
	@PostMapping("/processdetails")
	public ResponseEntity<?> processDetails(@RequestHeader(name = "Authorization", required = true) String token,
			@RequestBody ProcessRequestDTO requestDTO) throws PriorityException, InvalidClientException {

		if (!validClient.validDto(token).isValidStatus()) {
			return new ResponseEntity<>(new InvalidClientException("Invalid Client Exception"),
					HttpStatus.UNAUTHORIZED);
		}
		if (requestDTO.isPriorityRequest() && requestDTO.getComponentType().equalsIgnoreCase("Accessory")) {
			return new ResponseEntity<>(
					new PriorityException("Invalid request, Accessory can not bew a priority request"),
					HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(service.processDetails(requestDTO, token), HttpStatus.OK);
	}

	@CrossOrigin
	@PostMapping("/completeprocessing/{requestId}")
	public ResponseEntity<?> completeProcessing(@RequestHeader(name = "Authorization", required = true) String token,
			@RequestBody CreditCardDto cardDto, @PathVariable int requestId) throws Exception {
		if (!validClient.validDto(token).isValidStatus()) {
			return new ResponseEntity<>(new InvalidClientException("Invalid Token, please try signing in first"),
					HttpStatus.UNAUTHORIZED);
		}
		String result = service.confirmTransaction(cardDto, requestId, token);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@CrossOrigin
	@GetMapping("/trackrequest/{requestId}")
	public String trackRequest(@PathVariable int requestId) {
		return trackingService.trackRequest(requestId);
	}

	@PostMapping("/cancelrequest/{requestId}")
	public String cancelRequest(@RequestHeader(name = "Authorization", required = true) String token,
			@RequestBody CreditCardDto cardDto, @PathVariable int requestId) throws Exception {

		return "Service Under Construction";
	}
}
