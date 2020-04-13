package br.com.recatalog.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.recatalog.app.configuration.GitConfiguration;
import br.com.recatalog.app.service.CatalogService;
import br.com.recatalog.util.PropertyList;

@Controller
@RequestMapping("recatalog") 
public class CatalogController {
	
	@Autowired
	private GitConfiguration gitConfig;
	
	@Autowired
	private CatalogService catalogService;
	
	@Value("${urlBase}")
	private String urlBase;
	
	@GetMapping("") 
	public String home() {
		return "index.html";
	}
	
	@GetMapping("/catalog.html") 
	public String catalog() {
		return "catalog.html";
	}
	
	
	@GetMapping("/defCatalog.html") 
	public String defCatalog() {
		return "DefCatalog.html";
	}
	
	@GetMapping("/project.html") 
	public String project() {
		return "project.html";
	}
	
	@PostMapping("/addCatalog")
	public String addCatalog(@RequestParam(name = "name") String catName, @RequestParam String description, Model model) {
		
//		  CatalogService catalogService;
		 
//		  catalogService = new CatalogService();
		  
		  PropertyList propertyList = new PropertyList();
		  
		  System.out.println("Name: " + catName);
		  System.out.println("Description: " + description);

		  propertyList.addProperty("NAME", catName);
		  propertyList.addProperty("DESCRIPTION", description);
		  
		  catalogService.addCatalogItem(propertyList);
		 
		model.addAttribute("msg", "success");
		return "catalog.html";
	}
	
	@PostMapping("/defCatalog")
	public String defCatalog(@RequestParam(name = "catName") String catName, @RequestParam String description, Model model) {
		
//		  CatalogService catalogService;
		 
//		  catalogService = new CatalogService();
		  
		  PropertyList propertyList = new PropertyList();
		  
		/*
		 * System.out.println("Name: " + catName); System.out.println("Description: " +
		 * description);
		 */
		  
		  if(catName.isEmpty()) return "defCatalog.html";

		  propertyList.addProperty("NAME", catName);
		  propertyList.addProperty("DESCRIPTION", description);
		  
		  catalogService.addCatalogItem(propertyList);
		  
		  if(propertyList.hasProperty("EXCEPTION")) {
			  model.addAttribute("msg", "error");
		  }
		  else 	model.addAttribute("msg", "success");
		return "defCatalog.html";
	}
	
	@GetMapping("/git-config") 
	@ResponseBody
	public String gitConfig() {
		System.out.println(gitConfig.getUrlBase());
//		return gitConfig.getUrlBase();
		return urlBase;
	}
}