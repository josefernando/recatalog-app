package br.com.recatalog.app;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.recatalog.app.domain.repository.CatalogRepository;
import br.com.recatalog.app.security.repository.SpringUsersRepository;

@SpringBootApplication
public class RecatalogApp {

	public static void main(String[] args) {
		SpringApplication.run(RecatalogApp.class, args);
	}
	
	@Autowired
	CatalogRepository catalogRepository;
	
	@Autowired
	SpringUsersRepository usersRepository;
	
	@PostConstruct
	void init() {
		System.out.println("cats:" + catalogRepository.findAll());
		System.out.println("users:" + usersRepository.findAll());		
	}
}