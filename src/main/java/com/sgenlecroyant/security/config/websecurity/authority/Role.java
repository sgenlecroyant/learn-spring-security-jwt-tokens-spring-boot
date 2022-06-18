package com.sgenlecroyant.security.config.websecurity.authority;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Role {
	
	REG_USER(Set.of(Permission.BOOK_READ)),
	ADMIN(Set.of(Permission.BOOK_READ, Permission.BOOK_WRITE));
	
	private Set<Permission> permissions;
	
	private Role(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	public Set<Permission> getPermissions() {
		return permissions;
	}
	
	public Set<? extends GrantedAuthority> getGrantedAuthorities(){
		
		Set<SimpleGrantedAuthority> grantedAuthorities = this.getPermissions()
			.stream()
			.map((permission) -> new SimpleGrantedAuthority(permission.getPermission()))
			.collect(Collectors.toSet());
		
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" +this.name()));
		
		return grantedAuthorities;
		
	}
	
	public String getRole() {
		return this.name();
	}
	
}
