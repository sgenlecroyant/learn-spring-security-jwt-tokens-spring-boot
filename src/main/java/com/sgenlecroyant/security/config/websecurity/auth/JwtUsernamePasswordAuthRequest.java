package com.sgenlecroyant.security.config.websecurity.auth;

public class JwtUsernamePasswordAuthRequest {
	
	private String username;
	private String password;
	
	public JwtUsernamePasswordAuthRequest() {
	}

	public JwtUsernamePasswordAuthRequest(String username, String password) {
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
