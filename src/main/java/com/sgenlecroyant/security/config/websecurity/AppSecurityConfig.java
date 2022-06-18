package com.sgenlecroyant.security.config.websecurity;
//package com.sgenlecroyant.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

import com.sgenlecroyant.security.config.websecurity.authority.Role;

// @formatter:off
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class AppSecurityConfig {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	public SecurityFilterChain getWebSecurityConfigurer(HttpSecurity httpSecurity) throws Exception {

		DefaultSecurityFilterChain webSecurityConfigurer = 
				
				httpSecurity.csrf().disable()
							.headers().frameOptions().disable()
							.and()
							.authorizeHttpRequests()
							.antMatchers(HttpMethod.GET, "/", "/css", "/js").hasRole(Role.ADMIN.getRole())
							.antMatchers("/h2-console/**").permitAll()
//							.antMatchers(HttpMethod.POST, "/books/**").hasRole(Role.ADMIN.getRole())
//							.antMatchers(HttpMethod.PATCH, "/books/**").hasRole(Role.ADMIN.getRole())
//							.antMatchers(HttpMethod.DELETE, "/books/**").hasRole(Role.ADMIN.getRole())
//							.antMatchers(HttpMethod.GET, "/books/**").hasAnyRole(Role.ADMIN.getRole())
							.anyRequest().authenticated()
								.and()
								.httpBasic()
//								.formLogin()
								.and()
								.build();
		
		return webSecurityConfigurer;
	}

//	@Bean
	public UserDetailsManager getUsersDetails() {

		
		UserDetails peterson = User.builder()
								.username("peterson")
								.password(this.passwordEncoder.encode("password"))
								.authorities(Role.REG_USER.getGrantedAuthorities())
								.build();
		
		UserDetails smith = User.builder()
								.username("smith")
								.password(this.passwordEncoder.encode("password"))
								.authorities(Role.ADMIN.getGrantedAuthorities())
								.build();
		
		return new InMemoryUserDetailsManager(peterson, smith);
	}

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
