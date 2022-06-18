package com.sgenlecroyant.security.config.websecurity.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements UserDetailsService{
	
	
	private UserDao userDao;
	
	@Autowired
	public AppUserService(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return this.userDao.selectAppUserByUsername(username)
						.orElseThrow(() -> new UsernameNotFoundException("Username is invalid: " +username));
	}
	
	

}
