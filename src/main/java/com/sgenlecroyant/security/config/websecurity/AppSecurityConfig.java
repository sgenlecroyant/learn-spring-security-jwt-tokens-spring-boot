package com.sgenlecroyant.security.config.websecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

// @formatter:off
@Configuration
@EnableWebSecurity
public class AppSecurityConfig {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	public SecurityFilterChain getWebSecurityConfigurer(HttpSecurity httpSecurity) throws Exception {

		DefaultSecurityFilterChain webSecurityConfigurer = 
				
				httpSecurity.csrf().disable()
							.authorizeHttpRequests()
							.antMatchers(HttpMethod.GET, "/", "/css", "/js").permitAll()
							.anyRequest().authenticated()
								.and()
								.httpBasic()
								.and()
								.build();

		return webSecurityConfigurer;
	}

	@Bean
	public UserDetailsManager getUsersDetails() {

		
		UserDetails peterson = User.builder()
								.username("peterson")
								.password(this.passwordEncoder.encode("password"))
								.roles("REG_USER").build();
		
		UserDetails smith = User.builder()
								.username("smith")
								.password(this.passwordEncoder.encode("password"))
								.roles("ADMIN").build();
		
		return new InMemoryUserDetailsManager(peterson, smith);
	}

}
