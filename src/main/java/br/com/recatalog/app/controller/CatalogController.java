package br.com.recatalog.app.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.recatalog.app.model.Catalog;
import br.com.recatalog.app.model.CatalogItem;
import br.com.recatalog.app.model.PropertyCatalog;
import br.com.recatalog.app.service.CatalogService;
import br.com.recatalog.util.PropertyList;

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
	
	@PostMapping("/addCatalog")
	public String addCatalog(@RequestParam(name = "name") String catName, @RequestParam String description, Model model) {
		
		  CatalogService catalogService;
		 
		  catalogService = new CatalogService();
		  
		  PropertyList propertyList = new PropertyList();
		  
		  System.out.println("Name: " + catName);
		  System.out.println("Description: " + description);

		  propertyList.addProperty("NAME", catName);
		  propertyList.addProperty("DESCRIPTION", description);
		  
		  catalogService.addCatalogItem(propertyList);
		 
		model.addAttribute("msg", "success");
		return "catalog.html";
	}	
}