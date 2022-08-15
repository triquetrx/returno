package com.cognizant.project.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public @Data @AllArgsConstructor @NoArgsConstructor class CreditCard {
	
	@Id
	private long creditCardNumber;
	private String creditCardHolder;
	private double creditCardLimit;
	private int cvv;
	private double processingCharge;
	private double balance;

}
