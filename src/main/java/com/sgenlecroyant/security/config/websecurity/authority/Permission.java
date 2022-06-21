//package com.sgenlecroyant.security;
package com.sgenlecroyant.security.config.websecurity.authority;

public enum Permission {
	
	BOOK_READ("book:read"),
	BOOK_WRITE("book:write");
	
	private String permission;
	
	private Permission(String permission) {
		this.permission = permission;
	}

	public String getPermission() {
		return permission;
	}

}
