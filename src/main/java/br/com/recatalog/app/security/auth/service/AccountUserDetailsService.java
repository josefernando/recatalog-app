package br.com.recatalog.app.security.auth.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.recatalog.app.security.auth.model.Account;
import br.com.recatalog.app.security.auth.model.Permission;
import br.com.recatalog.app.service.AccountService;

public class AccountUserDetailsService implements UserDetailsService {
	
	@Autowired
	AccountService accountService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Account account = accountService.findById(username);
		
		if(account == null) {
			return null;
		}
		
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		
		for(Permission permission : account.getPermissions()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(permission.getPermissionId()));
		}
		
		UserDetails userDetails = new User(account.getUserLogin()
				, account.getPassword()
				, account.isEnabled()
				, !account.isAccountExpired()
				, !account.isCredentialsExpired()
				, !account.isAccountLocked()
				, grantedAuthorities
				);
		
		return userDetails;
	}
}