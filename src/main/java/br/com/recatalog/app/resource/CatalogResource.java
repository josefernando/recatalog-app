package br.com.recatalog.app.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.recatalog.app.dto.CatalogDTO;
import br.com.recatalog.app.service.CatalogService;

@RestController
@RequestMapping("api")
public class CatalogResource {
	
	@Autowired
	CatalogService catalogService;
	
	@GetMapping("catalogs")
	public List<CatalogDTO> catalogs() {
		List<CatalogDTO> catalogsDto = catalogService.listCatalogs();
		return catalogsDto;
	}

}