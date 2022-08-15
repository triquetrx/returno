package com.cognizant.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public @Data @AllArgsConstructor @NoArgsConstructor class ConfirmProcessRequest {
	
	private long creditCardNumber;
	private String creditCardHolder;
	private int cvv;
	private double totalCharge;

}
