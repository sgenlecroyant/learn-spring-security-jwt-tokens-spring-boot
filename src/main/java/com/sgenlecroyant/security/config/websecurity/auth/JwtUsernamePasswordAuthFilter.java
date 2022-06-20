package com.sgenlecroyant.security.config.websecurity.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtUsernamePasswordAuthFilter extends UsernamePasswordAuthenticationFilter{

	private AuthenticationManager authenticationManager;
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public JwtUsernamePasswordAuthFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		try {
			JwtUsernamePasswordAuthRequest usernamePasswordAuthRequest = this.objectMapper.readValue(request.getInputStream(), JwtUsernamePasswordAuthRequest.class);
			
			Authentication authenticationRequest = new UsernamePasswordAuthenticationToken(usernamePasswordAuthRequest.getUsername(), usernamePasswordAuthRequest.getPassword(), null);
			Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);
			return authenticationResponse;
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}

	@Override
	protected String obtainUsername(HttpServletRequest request) {
		String username = super.obtainUsername(request);
		return username;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		
	}
	
	

}
