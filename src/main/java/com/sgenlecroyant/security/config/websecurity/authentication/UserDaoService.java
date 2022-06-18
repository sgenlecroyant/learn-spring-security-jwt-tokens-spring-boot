package com.sgenlecroyant.security.config.websecurity.authentication;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sgenlecroyant.security.config.websecurity.authority.Role;
import com.sgenlecroyant.security.user.entity.AppUser;

@Service
public class UserDaoService implements UserDetailsService{

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return this.getAppUsers()
					.stream()
					.filter((appUser) -> appUser.getUsername().equals(username))
					.findFirst()
					.orElseThrow(() -> new UsernameNotFoundException("username not found: " +username));
	}
	
	public List<AppUser> getAppUsers(){
		
		AppUser admin = 
				new AppUser("James", "Bond", "james@gmail.com", this.passwordEncoder.encode("password"), true, true, true, true, Role.ADMIN);
		
		AppUser regUser = 
				new AppUser("smith", "Jay", "smith@gmail.com", this.passwordEncoder.encode("password"), true, true, true, true, Role.ADMIN);
		return Arrays.asList(admin, regUser);
	}
	

}
