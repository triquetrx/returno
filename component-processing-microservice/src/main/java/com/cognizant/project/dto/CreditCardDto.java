package com.cognizant.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public @Data @NoArgsConstructor @AllArgsConstructor class CreditCardDto {
	
	private long creditCardNumber;
	private String creditCardHolder;
	private int cvv;
	private double totalCharge;
}
