package com.sgenlecroyant.security.user.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.sgenlecroyant.security.config.websecurity.authority.Role;

@Entity(name = "users")
@Table(name = "users")
public class AppUser implements UserDetails{
	
	@Id
	private Long id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private boolean isEnable;
	private boolean isAccountNonLocked;
	private boolean isAccountNonExpired;
	private boolean isCredentialsNonExpired;
	private Role role;
	
	
	public AppUser() {
		// TODO Auto-generated constructor stub
	}

	public AppUser(String firstName, String lastName, String username, String password, boolean isEnable,
			boolean isAccountNonLocked, boolean isAccountNonExpired, boolean isCredentialsNonExpired, Role role) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.isEnable = isEnable;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isAccountNonExpired = isAccountNonExpired;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.role = role;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.role.getGrantedAuthorities();
	}

	public Long getId() {
		return id;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.isEnable;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

}
