package com.demo.Exception;

public class DataNotFound extends RuntimeException{
	
	public DataNotFound() {
		super();
	}
	
	public DataNotFound(String msg) {
		super(msg);
	}
}
