package com.sgenlecroyant.security.config.websecurity.auth;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtUsernamePasswordAuthFilter extends UsernamePasswordAuthenticationFilter {
	@JsonSerialize(using = ResponseMessageSerializer.class)
	@JsonDeserialize(using = ResponseMessageDeserializer.class)
	Message message = new Message("Auhentication Failed", "Bad Credentials");

	private ObjectMapper objectMapper = new ObjectMapper();
	private final ProviderManager providerManager;

	public JwtUsernamePasswordAuthFilter(ProviderManager providerManager,
			AuthenticationProvider authenticationProvider) {
		this.providerManager = providerManager = new ProviderManager(authenticationProvider);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			JwtUsernamePasswordAuthRequest usernamePasswordAuthRequest = this.objectMapper
					.readValue(request.getInputStream(), JwtUsernamePasswordAuthRequest.class);

			Authentication authenticationRequest = new UsernamePasswordAuthenticationToken(
					usernamePasswordAuthRequest.getUsername(), usernamePasswordAuthRequest.getPassword(), null);
			Authentication authenticationResponse = this.providerManager.authenticate(authenticationRequest);
			return authenticationResponse;
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String secretKey = "helloworld helloworld helloworld helloworld";
		String token = Jwts.builder().setSubject(authResult.getName()).setIssuer("Supreme").setIssuedAt(new Date())
				.setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(1)))
				.claim("authorities", authResult.getAuthorities())
				.signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
				.setId(UUID.randomUUID().toString()).compact();
		response.addHeader(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", token));
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {


	}

}
