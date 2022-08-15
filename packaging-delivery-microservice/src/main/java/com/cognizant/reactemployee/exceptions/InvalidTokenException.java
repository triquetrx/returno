package com.cognizant.reactemployee.exceptions;

public class InvalidTokenException extends Exception{

	private static final long serialVersionUID = 9220816694319439575L;

	public InvalidTokenException(String message) {
		super(message);
	}
	
}
