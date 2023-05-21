package com.bikkadit.blog.exceptions;

public class ApiException extends RuntimeException {
	// exception handeling for wrong password input while login

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ApiException(String message) {
		super(message);
	} 
	
	public ApiException() {
		super();
	}

}
