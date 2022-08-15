package com.cognizant.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public @AllArgsConstructor @Data @NoArgsConstructor class PackagingAndDeliveryDTO {
	
	private int packagingCost;
	private int deliveryCost;
	@JsonProperty
	private Double totalCharge;

}
