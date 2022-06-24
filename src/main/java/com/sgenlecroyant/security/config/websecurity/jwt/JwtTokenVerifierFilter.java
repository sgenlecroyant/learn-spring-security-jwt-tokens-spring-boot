package com.sgenlecroyant.security.config.websecurity.jwt;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtTokenVerifierFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String someSecretKey = "helloworld helloworld helloworld helloworld";
		
		String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		String token = null;
		
		if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			response.setContentType(MediaType.TEXT_PLAIN_VALUE);
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.getOutputStream().println("Untrustable Token");
			response.getWriter().println("some other additional information ..");
			return;
		}else {
			token = authorizationHeader.replace("Bearer ", Strings.EMPTY);
			
			@SuppressWarnings("deprecation")
			Jws<Claims> claims = Jwts.parser()
				.setSigningKey(Keys.hmacShaKeyFor(someSecretKey.getBytes()))
				.parseClaimsJws(token);
			
			Claims body = claims.getBody();
			String username = body.getSubject();
			
			@SuppressWarnings("unchecked")
			List<Map<String, String>> autorities = (List<Map<String, String>>)body.get("authorities");
			
			Set<SimpleGrantedAuthority> grantedAuthorities = autorities.stream()
						.map((autority) -> new SimpleGrantedAuthority(autority.get("authority")))
						.collect(Collectors.toSet());
			Authentication jwtTokenAuthenticationRequest = new UsernamePasswordAuthenticationToken(username, null, grantedAuthorities);
			
			SecurityContextHolder.getContext().setAuthentication(jwtTokenAuthenticationRequest);
			filterChain.doFilter(request, response);
			return;
		}
		
	}

}
