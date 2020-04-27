package br.com.recatalog.app;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.GrantedAuthority;

import br.com.recatalog.app.domain.repository.CatalogRepository;
import br.com.recatalog.app.model.userdetails.CustomGrantedAuthority;
import br.com.recatalog.app.model.userdetails.CustomUserDetails;
import br.com.recatalog.app.security.repository.SpringUsersRepository;
import br.com.recatalog.app.userdetails.repository.CustomUserDetailsRepository;

@SpringBootApplication
public class RecatalogApp {

	public static void main(String[] args) {
		SpringApplication.run(RecatalogApp.class, args);
	}
	
	@Autowired
	CatalogRepository catalogRepository;
	
	@Autowired
	SpringUsersRepository usersRepository;
	
	@Autowired
	CustomUserDetailsRepository userDetailsRepository;
	
	@PostConstruct
	void init() {
		System.out.println("cats:" + catalogRepository.findAll());
		System.out.println("users:" + usersRepository.findAll());	
		List<CustomUserDetails> cud = userDetailsRepository.findAll();
		CustomUserDetails item = cud.get(0);
		System.out.println("user details:" + cud.get(0).getUsername());	
		System.out.println("first authority:" + item.getPassword());
		
		CustomGrantedAuthority cga = item.getGrantedAuthorities().get(0);
		System.out.println("first size:" + cga.getAuthority());		


	}
}