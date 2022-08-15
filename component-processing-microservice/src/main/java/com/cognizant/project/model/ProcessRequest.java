package com.cognizant.project.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public @Data @AllArgsConstructor @NoArgsConstructor class ProcessRequest {
	
	@Id
	private int id;
	private String name;
	private Long contactNumber;
	private String componentName;
	private String componentType;
	private int quantity;
	private boolean priorityRequest;
	private boolean requestDone;
	private boolean paymentDone;
	
}
