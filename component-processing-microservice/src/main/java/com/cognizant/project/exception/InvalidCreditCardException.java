package com.cognizant.project.exception;

public class InvalidCreditCardException extends Exception {

	private static final long serialVersionUID = 2731160897834699027L;

	public InvalidCreditCardException(String message) {
		super(message);
	}

}
