package com.app.recruitment.exception;


 

public class GenericErrorResponse {
	private String message;
	private String error;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	
}
