package com.sgenlecroyant.security.config.websecurity.auth;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.lang.Strings;
import io.jsonwebtoken.security.Keys;

public class JwtTokenVerifierFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String secretKey = "helloworld helloworld helloworld helloworld";

		String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			throw new RuntimeException("Invalid Token");
		} else {
			String token = authorizationHeader.replace("Bearer ", "");

//			Set<GrantedAuthority> grantedAuthorities = authorities
//						.stream()
//						.map((authority) -> new SimpleGrantedAuthority(authority.get("authority")))
//						.collect(Collectors.toSet());
//			
//			Authentication authenticationRequest = new UsernamePasswordAuthenticationToken(username, null, grantedAuthorities);
//			
//			SecurityContextHolder.getContext().setAuthentication(authenticationRequest);
//			filterChain.doFilter(request, response);
		}

	}

}
