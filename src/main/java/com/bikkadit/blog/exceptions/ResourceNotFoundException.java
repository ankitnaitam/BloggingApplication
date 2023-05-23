package com.bikkadit.blog.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{

	public ResourceNotFoundException(String message) {
		super(message);
	}

	public ResourceNotFoundException() {
		super();
	}

	public ResourceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	
//	String resourceName;
//	String fieldName;
//	Integer fieldValue;
//
//	public ResourceNotFoundException(String resourceName, String fieldName, Integer fieldValue) {
//		super(String.format("%s not found with %s : %s", resourceName, fieldName, fieldValue));
//		this.resourceName = resourceName;
//		this.fieldName = fieldName;
//		this.fieldValue = fieldValue;
//	}
	
}
