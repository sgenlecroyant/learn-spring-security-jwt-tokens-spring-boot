package com.sgenlecroyant.security.config.websecurity;
//package com.sgenlecroyant.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

import com.sgenlecroyant.security.config.websecurity.auth.JwtUsernamePasswordAuthFilter;
import com.sgenlecroyant.security.config.websecurity.authentication.AppUserService;

// @formatter:off
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(jsr250Enabled = true, prePostEnabled = true, securedEnabled = true)
public class AppSecurityConfig {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AppUserService appUserService;
	

	@Bean
	public SecurityFilterChain getWebSecurityConfigurer(HttpSecurity httpSecurity) throws Exception {
		
		DefaultSecurityFilterChain webSecurityConfigurer = 
				
				httpSecurity.csrf().disable()
				.addFilter(new JwtUsernamePasswordAuthFilter(new ProviderManager(this.getAuthenticationManager(), this.getAuthenticationManager()), this.getAuthenticationManager()))
							.headers().frameOptions().disable()
							
							.and()
							.authorizeHttpRequests()
							.anyRequest().authenticated()
								.and()
								.httpBasic()
//								.formLogin()
								.and()
								.build();
		
		return webSecurityConfigurer;
	}
	
//	@Bean
//	public AuthenticationManager getAuthenticationManager(AuthenticationConfiguration authConfigurer) throws Exception {
//		AuthenticationManager authenticationManager = authConfigurer.getAuthenticationManager();
//		
//		 return authenticationManager;
//	}

	
	protected void configureAuthenticationManager(AuthenticationManagerBuilder auth) throws Exception {
		AuthenticationManagerBuilder authenticationProvider = auth.authenticationProvider(getAuthenticationManager());
	}
	
	@Bean
	public AuthenticationProvider getAuthenticationManager() {
		DaoAuthenticationProvider daoAuthenticationProvider = 
				new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(appUserService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
		return daoAuthenticationProvider;
	}

//	@Bean
//	public UserDetailsManager getUsersDetails() {
//
//		
//		UserDetails peterson = User.builder()
//								.username("peterson")
//								.password(this.passwordEncoder.encode("password"))
//								.authorities(Role.REG_USER.getGrantedAuthorities())
//								.build();
//		
//		UserDetails smith = User.builder()
//								.username("smith")
//								.password(this.passwordEncoder.encode("password"))
//								.authorities(Role.ADMIN.getGrantedAuthorities())
//								.build();
//		
//		return new InMemoryUserDetailsManager(peterson, smith);
//	}

//	@Override
//	protected void configure(HttpSecurity httpSecurity) throws Exception {
//		
//		httpSecurity.csrf().disable()
//		.headers().frameOptions().disable()
//		.and()
//		.authorizeHttpRequests()
//		.antMatchers(HttpMethod.GET, "/", "/css", "/js").hasRole(Role.ADMIN.getRole())
//		.antMatchers("/h2-console/**").permitAll()
////		.antMatchers(HttpMethod.POST, "/books/**").hasRole(Role.ADMIN.getRole())
////		.antMatchers(HttpMethod.PATCH, "/books/**").hasRole(Role.ADMIN.getRole())
////		.antMatchers(HttpMethod.DELETE, "/books/**").hasRole(Role.ADMIN.getRole())
//		.antMatchers(HttpMethod.GET, "/books/**").hasAnyRole(Role.ADMIN.getRole())
//		.anyRequest().authenticated()
//			.and()
//			.httpBasic();
////			.formLogin()
//	}
	
	
	
	
	
	

}
