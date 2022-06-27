package com.sgenlecroyant.security.config.websecurity.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtUsernamePasswordAuthFilter extends UsernamePasswordAuthenticationFilter{

	private ObjectMapper objectMapper = new ObjectMapper();
	private final ProviderManager providerManager;
	
	
	public JwtUsernamePasswordAuthFilter(ProviderManager providerManager, AuthenticationProvider authenticationProvider) {
		this.providerManager = providerManager = new ProviderManager(authenticationProvider);
	}
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		try {
			JwtUsernamePasswordAuthRequest usernamePasswordAuthRequest = this.objectMapper.readValue(request.getInputStream(), JwtUsernamePasswordAuthRequest.class);
			
			Authentication authenticationRequest = new UsernamePasswordAuthenticationToken(usernamePasswordAuthRequest.getUsername(), usernamePasswordAuthRequest.getPassword(), null);
			Authentication authenticationResponse = this.providerManager.authenticate(authenticationRequest);
			return authenticationResponse;
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		System.out.println("Auth Success !");
		
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		System.out.println("Auth Failed!");
	}
	
	

}
