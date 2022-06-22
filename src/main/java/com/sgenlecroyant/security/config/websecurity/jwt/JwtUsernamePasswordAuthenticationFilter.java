package com.sgenlecroyant.security.config.websecurity.jwt;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

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
		String someSecretKey = "helloworld helloworld helloworld helloworld";
		String token = Jwts.builder()
			.setSubject(authResult.getName())
			.claim("authorities", authResult.getAuthorities())
			.setIssuer("Supreme")
			.setIssuedAt(new Date())
			.setAudience("Supreme API consumer")
			.setId(UUID.randomUUID().toString())
			.signWith(Keys.hmacShaKeyFor(someSecretKey.getBytes(StandardCharsets.UTF_8)))
			.setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(2)))
			.compact();
		
		response.addHeader(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", token));
		
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		System.out.println("Auth Failed: You can perform other business logic here, like brute forcing prevention, ...");
	}	
}
