package br.com.recatalog.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("recatalog") 
public class CatalogController {
	@GetMapping("") 
	public String home() {
		return "index.html";
	}
	
	@GetMapping("/catalog.html") 
	public String catalog() {
		return "catalog.html";
	}
	
	@GetMapping("/project.html") 
	public String project() {
		return "project.html";
	}
}