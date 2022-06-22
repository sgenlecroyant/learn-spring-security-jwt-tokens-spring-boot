package com.sgenlecroyant.security.config.websecurity;
//package com.sgenlecroyant.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.sgenlecroyant.security.config.websecurity.authority.Role;

// @formatter:off
@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = false)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter{
	

	@Autowired
	private PasswordEncoder passwordEncoder;

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

	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		
		UserDetails admin = User.builder()
								.username("admin")
								.password(this.passwordEncoder.encode("password"))
								.roles(Role.ADMIN.getRole())
								.build();
		UserDetails regularUser = User.builder()
				.username("user")
				.password(this.passwordEncoder.encode("password"))
				.roles(Role.REG_USER.getRole())
				.build();
		
		return new InMemoryUserDetailsManager(admin, regularUser);
	}
	
	
	
	
	
	

	
	
	

}
