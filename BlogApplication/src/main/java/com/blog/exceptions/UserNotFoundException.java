package com.blog.exceptions;

public class UserNotFoundException extends RuntimeException{

	public UserNotFoundException() {
		// TODO Auto-generated constructor stub
	}
	
	public UserNotFoundException(String message){
		super(message);
	}
	
}
