package com.cognizant.project.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public @Data @NoArgsConstructor @AllArgsConstructor class PaymentHistory {

	@Id
	private int requestId;
	private double totalAmount;
	
}
