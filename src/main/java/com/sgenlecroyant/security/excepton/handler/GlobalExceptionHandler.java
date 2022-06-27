package com.sgenlecroyant.security.excepton.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sgenlecroyant.security.excepton.CustomBadCredentialsException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(value = CustomBadCredentialsException.class)
	public ResponseEntity<CustomBadCredentialsException> handleBadCredentials(CustomBadCredentialsException e, WebRequest webRequest){
		
		CustomBadCredentialsException exception = 
				new CustomBadCredentialsException(e.getMessage(), webRequest.getDescription(false));
		
		return new ResponseEntity<CustomBadCredentialsException>(exception, HttpStatus.UNAUTHORIZED);
	}

}
