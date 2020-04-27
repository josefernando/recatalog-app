package br.com.recatalog.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.recatalog.app.model.userdetails.CustomUserDetails;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	CustomUserDetails userDetails;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		
		
		return null;
	}

}
