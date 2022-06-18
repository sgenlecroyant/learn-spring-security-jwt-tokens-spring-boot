package com.sgenlecroyant.security.config.websecurity.authority;

import java.util.Set;

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
	
}
