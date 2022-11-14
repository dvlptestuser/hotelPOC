package com.hotel.model;

public class ErrorResponse {

	private String userMessage;
	private String systemMessage;

	public ErrorResponse() {
	}
	public ErrorResponse(String userMessage, String systemMessage) {
		super();
		this.userMessage = userMessage;
		this.systemMessage = systemMessage;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	public String getSystemMessage() {
		return systemMessage;
	}

	public void setSystemMessage(String systemMessage) {
		this.systemMessage = systemMessage;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
