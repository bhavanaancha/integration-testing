package com.epam.demo.exceptions;

@SuppressWarnings("serial")
public class BatchException extends RuntimeException{
	public BatchException(String message) {
		super(message);
	}

}
