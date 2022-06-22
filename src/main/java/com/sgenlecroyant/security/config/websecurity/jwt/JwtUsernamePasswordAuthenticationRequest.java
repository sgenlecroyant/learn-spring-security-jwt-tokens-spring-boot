package com.sgenlecroyant.security.config.websecurity.jwt;


public class JwtUsernamePasswordAuthenticationRequest {
	
	private String username;
	private String password;
	
	public JwtUsernamePasswordAuthenticationRequest() {
		// TODO Auto-generated constructor stub
	}

	public JwtUsernamePasswordAuthenticationRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	
}
