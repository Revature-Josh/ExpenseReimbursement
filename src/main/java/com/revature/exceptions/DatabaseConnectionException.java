package com.revature.exceptions;

@SuppressWarnings("serial")
public class DatabaseConnectionException extends Exception {

	public DatabaseConnectionException() {
		super();
	}

	public DatabaseConnectionException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DatabaseConnectionException(String message, Throwable cause) {
		super(message, cause);
	}

	public DatabaseConnectionException(String message) {
		super(message);
	}

	public DatabaseConnectionException(Throwable cause) {
		super(cause);
	}

}
