package com.revature.exception;

public class UsernameIsAlreadyTakenException extends RuntimeException{

	public UsernameIsAlreadyTakenException() {}
	
	public UsernameIsAlreadyTakenException(String msg) {
		super(msg);
	}
	
}
