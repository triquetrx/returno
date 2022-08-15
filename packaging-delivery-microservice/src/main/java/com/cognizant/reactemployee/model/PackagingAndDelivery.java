package com.cognizant.reactemployee.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
public @Data @AllArgsConstructor @NoArgsConstructor class PackagingAndDelivery {
	
	private int id;
	private int packagingCost;
	private int deliveryCost;

}
