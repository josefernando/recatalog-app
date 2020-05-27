package br.com.recatalog.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.recatalog.app.model.domain.Catalog;
import br.com.recatalog.app.service.CatalogService;
import br.com.recatalog.app.util.BreadCrumbSession;
import br.com.recatalog.util.PropertyList;

@Controller
@RequestMapping("recatalog")
public class CatalogController {

	/*
	 * @Autowired private GitConfiguration gitConfig;
	 */

	@Autowired
	private CatalogService catalogService;
	
	@Autowired
	private BreadCrumbSession breadCrumbSession;

	@GetMapping("")
	public String home() {
		return "index.html";
	}
	
	@PostMapping("/index")
	public String index() {
		return "index.html";
	}

	/*
	@GetMapping("/catalog.html")
	public String catalog() {
		return "catalog.html";
	}
	*/
	
	// regex assegura case insensitive . veja configuração Spring
	//@GetMapping("/catalog.html")
//	@GetMapping("/{id:[Cc][Aa][Tt][Aa][Ll][Oo][Gg][.][Hh][Tt][Mm][Ll]}")
	@GetMapping("/catalog.html")
	public String catalog(@RequestParam(required = false, defaultValue="") String catname
			, @RequestParam(required = false, defaultValue="") String projname 
			, @RequestParam(required = false, defaultValue="") String codename) {
		breadCrumbSession.setCatalogName(catname); 
		breadCrumbSession.setProjectName(projname); 
		breadCrumbSession.setCodeName(codename); 

		return "catalog.html";
	}

	@GetMapping("/defCatalog.html")
	public String defCatalog() {
		return "DefCatalog.html";
	}

//	@GetMapping("/project.html")
//	@GetMapping("/{id:[Pp][Rr][Oo][Jj][Ee][Cc][Tt][.][Hh][Tt][Mm][Ll]}")
//	public String project(@PathVariable("id") String id) {
	@GetMapping("/project.html")
	public String project(@RequestParam(required = false, defaultValue="") String catname
			, @RequestParam(required = false, defaultValue="") String projname 
			, @RequestParam(required = false, defaultValue="") String codename) {
		
		breadCrumbSession.setCatalogName(catname); 
		breadCrumbSession.setProjectName(projname); 
		breadCrumbSession.setCodeName(codename); 
		return "project.html";
	}

	/*
	 * @PostMapping("/addCatalog") public String addCatalog(@RequestParam(name =
	 * "name") String catName, @RequestParam String description, Model model) {
	 * 
	 * // CatalogService catalogService;
	 * 
	 * // catalogService = new CatalogService();
	 * 
	 * PropertyList propertyList = new PropertyList();
	 * 
	 * System.out.println("Name: " + catName); System.out.println("Description: " +
	 * description);
	 * 
	 * propertyList.addProperty("NAME", catName);
	 * propertyList.addProperty("DESCRIPTION", description);
	 * 
	 * catalogService.addCatalogItem(propertyList);
	 * 
	 * model.addAttribute("msg", "success"); return "catalog.html"; }
	 */

	/*
	 * @PostMapping("/defCatalog") public String defCatalog(@RequestParam(name =
	 * "catName") String catName, @RequestParam String description, Model model) {
	 * 
	 * // CatalogService catalogService;
	 * 
	 * // catalogService = new CatalogService();
	 * 
	 * PropertyList propertyList = new PropertyList();
	 * 
	 * 
	 * System.out.println("Name: " + catName); System.out.println("Description: " +
	 * description);
	 * 
	 * 
	 * if(catName.isEmpty()) return "defCatalog.html";
	 * 
	 * propertyList.addProperty("NAME", catName);
	 * propertyList.addProperty("DESCRIPTION", description);
	 * 
	 * catalogService.addCatalogItem(propertyList);
	 * 
	 * if(propertyList.hasProperty("EXCEPTION")) { model.addAttribute("msg",
	 * "error"); } else model.addAttribute("msg", "success"); return
	 * "defCatalog.html"; }
	 */

	@PostMapping("/createCatalog")
	public String createCatalog(@RequestParam(name = "catalogName") String cataLogName,
			@RequestParam String description, Model model) {
		PropertyList propertyList = new PropertyList();

		if (cataLogName.isEmpty())
			return "catalog.html";

		propertyList.addProperty("NAME", cataLogName);
		propertyList.addProperty("DESCRIPTION", description);

		catalogService.addCatalogItem(propertyList);

		if (propertyList.hasProperty("EXCEPTION")) {
			model.addAttribute("msg", "error");
			breadCrumbSession.clearCatalog();
		} else {
			model.addAttribute("msg", "success");
			
			Catalog createdCatalog = (Catalog) propertyList.getProperty("ENTITY");
			breadCrumbSession.clearCatalog();
			breadCrumbSession.setCatalogId(createdCatalog.getId());
			breadCrumbSession.setCatalogName(createdCatalog.getName());
		}
		return "catalog.html";
	}

	@GetMapping("/catalogs")
	public String listCatalogs(@RequestParam(required = false, defaultValue="") String catname
			, @RequestParam(required = false, defaultValue="") String projname 
			, @RequestParam(required = false, defaultValue="") String codename
			, Model model) {
		
		breadCrumbSession.setCatalogName(catname); 
		breadCrumbSession.setProjectName(projname); 
		breadCrumbSession.setCodeName(codename); 
		model.addAttribute("catalogs", catalogService.listAllCatalogItens());

		return "catalog.html";
	}

	/*
	 * @GetMapping("/catalogsx") public String listAllCatalogs(Model model) {
	 * model.addAttribute("catalogs", catalogService.listAllCatalogItens()); return
	 * "listAllCatalogs.html"; }
	 */
}