package com.cognizant.componentprocessing.model;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {

	private static final long serialVersionUID = 7725147745848848020L;
	
	private final String token;
	private final boolean validStatus;

	public String getToken() {
		return token;
	}

	public boolean isValidStatus() {
		return validStatus;
	}
	
	public AuthenticationResponse(String jwttoken,boolean validStatus) {
		super();
		this.token = jwttoken;
		this.validStatus = validStatus;
	}


}
