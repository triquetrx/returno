package com.cognizant.project.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public @Data @AllArgsConstructor @NoArgsConstructor class ProcessResponse {
	
	@Id
	private int requestId;
	private double processingCharge;
	private double packagingAndDeliveryCharge;
	private Date dateOfDelivery;

}
