package com.sgenlecroyant.security.config.websecurity.auth;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@SuppressWarnings(value = "unused")
class Message {
	String message;
	String reason;
	
	public Message() {
		// TODO Auto-generated constructor stub
	}
	
	public Message(String message, String reason) {
		this.message = message;
		this.reason = reason;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}