package com.sgenlecroyant.security.config.websecurity;
//package com.sgenlecroyant.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.sgenlecroyant.security.config.websecurity.authority.Permission;
import com.sgenlecroyant.security.config.websecurity.authority.Role;

// @formatter:off
@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = false)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		http.csrf().disable()
		.headers().frameOptions().disable()
		.and()
		.authorizeHttpRequests()
		.antMatchers(HttpMethod.DELETE, "/books/**").hasRole(Role.ADMIN.getRole())
		.antMatchers(HttpMethod.POST, "/books/**").hasRole(Role.ADMIN.getRole())
		.antMatchers(HttpMethod.GET, "/books/**").hasAnyRole(Role.REG_USER.getRole())
		.anyRequest().authenticated()
			.and()
			.httpBasic();
//
	}
	
	
	

	
	
	

}
