package br.com.recatalog.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class RecatalogApp {

	public static void main(String[] args) {
		SpringApplication.run(RecatalogApp.class, args);
	}
}