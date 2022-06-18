package com.sgenlecroyant.security.config.websecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {

	@Bean
	public SecurityFilterChain getWebSecurityConfigurer(HttpSecurity httpSecurity) throws Exception {
		
		 DefaultSecurityFilterChain webSecurityConfigurer = httpSecurity.csrf().disable()
		.authorizeHttpRequests()
		.antMatchers(HttpMethod.GET, "/").permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.formLogin()
		.and()
		.build();
		 
		return webSecurityConfigurer;
	}
	
	

}
