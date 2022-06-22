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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
