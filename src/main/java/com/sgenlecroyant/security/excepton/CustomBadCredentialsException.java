package com.sgenlecroyant.security.excepton;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings(value = "serial")
@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class CustomBadCredentialsException extends RuntimeException{
	
	private String message;
	private String reason;
	
	public CustomBadCredentialsException(String message, String reason) {
		super(message);
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

	@Override
	public String getLocalizedMessage() {
		return super.getLocalizedMessage();
	}

	
	
}
