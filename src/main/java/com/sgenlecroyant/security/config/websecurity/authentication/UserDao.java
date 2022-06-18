package com.sgenlecroyant.security.config.websecurity.authentication;

import java.util.Optional;

import com.sgenlecroyant.security.user.entity.AppUser;

public interface UserDao {
	
	public Optional<AppUser> selectAppUserByUsername(String username);

}
