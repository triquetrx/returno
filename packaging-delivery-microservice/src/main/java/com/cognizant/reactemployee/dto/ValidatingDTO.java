package com.cognizant.reactemployee.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public @AllArgsConstructor @Data @NoArgsConstructor class ValidatingDTO {
	
	@JsonProperty
	private boolean validStatus;

}
