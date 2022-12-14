package com.demo.Exception;

public class AccessDeniedException extends RuntimeException{
	public AccessDeniedException() {
		super();
	}
	
	public AccessDeniedException(String msg) {
		super(msg);
	}
}
