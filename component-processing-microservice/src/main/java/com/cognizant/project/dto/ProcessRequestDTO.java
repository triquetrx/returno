package com.cognizant.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public @Data @AllArgsConstructor @NoArgsConstructor class ProcessRequestDTO {
	
	private String name;
	private Long contactNumber;
	private String componentName;
	private String componentType;
	private int quantity;
	private boolean priorityRequest;

}
