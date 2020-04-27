package br.com.recatalog.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.recatalog.app.model.userdetails.CustomUserDetails;
import br.com.recatalog.app.userdetails.repository.CustomUserDetailsRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	CustomUserDetailsRepository userDetailsRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<CustomUserDetails> optonalcdu = userDetailsRepository.findById(username);
		
		if(optonalcdu.isEmpty()) {
			throw new UsernameNotFoundException(username);
		}
		
		return optonalcdu.get();
	}
}
