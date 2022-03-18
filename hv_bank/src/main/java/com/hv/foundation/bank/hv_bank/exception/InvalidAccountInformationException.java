package com.hv.foundation.bank.hv_bank.exception;


public class InvalidAccountInformationException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	

	public InvalidAccountInformationException() {
		super();
	}

	public InvalidAccountInformationException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidAccountInformationException(String message) {
		super(message);
	}

	public InvalidAccountInformationException(Throwable cause) {
		super(cause);
	}

	
}
