package com.sgenlecroyant.security.config.websecurity.jwt;

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
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private AuthenticationManager authenticationManager;
	private ObjectMapper objectMapper = new ObjectMapper();
	private JwtUsernamePasswordAuthenticationRequest jwtUsernamePasswordAuthRequest;
	
	public JwtUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		try {
			jwtUsernamePasswordAuthRequest = this.objectMapper.readValue(request.getInputStream(), JwtUsernamePasswordAuthenticationRequest.class);
			
		    Authentication authenticationRequest = new UsernamePasswordAuthenticationToken(jwtUsernamePasswordAuthRequest.getUsername(), jwtUsernamePasswordAuthRequest.getPassword());
		    Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);
		    return authenticationResponse;
		} catch (IOException e) {
			throw new RuntimeException("Failed to read the auth request body Error => " +e.getMessage());
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		System.out.println("Auth Success");
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		System.out.println("Auth Failed");
	}	
}
