package com.revature.exceptions;

@SuppressWarnings("serial")
public class BadParamException extends Exception {
	
	
	public BadParamException() {
		super();
	}

	public BadParamException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BadParamException(String message, Throwable cause) {
		super(message, cause);
	}

	public BadParamException(String message) {
		super(message);
	}

	public BadParamException(Throwable cause) {
		super(cause);
	}

}
