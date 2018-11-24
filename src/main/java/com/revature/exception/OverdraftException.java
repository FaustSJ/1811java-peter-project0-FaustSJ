package com.revature.exception;

public class OverdraftException extends RuntimeException{
	
	public OverdraftException() {}
	
	public OverdraftException(String msg) {
		super(msg);
	}
	
}
