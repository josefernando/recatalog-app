package br.com.recatalog.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.recatalog.app.service.CatalogItemService;
//import br.com.recatalog.app.service.CatalogService;
import br.com.recatalog.util.PropertyList;

public class CatalogServiceTest {
	
	PropertyList properties;
	CatalogItemService catalogItemService;
	
	@BeforeEach
	public void init() {
		properties = new PropertyList();
		catalogItemService = new CatalogItemService();
	}
	
	@Test
	public void testAddCatalogItem() {
		
		properties.addProperty("NAME", "SINISTRO");
		properties.addProperty("DESCRIPTION", "Descrição Sinistro");

		catalogItemService.addCatalogItem(properties);
		
		assertTrue(true);
	}
}