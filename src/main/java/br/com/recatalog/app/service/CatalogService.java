package br.com.recatalog.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.recatalog.app.domain.repository.CatalogRepository;
import br.com.recatalog.app.model.domain.Catalog;
import br.com.recatalog.app.model.domain.CatalogItem;
import br.com.recatalog.util.PropertyList;

@Service
public class CatalogService {
	
	@Autowired
	CatalogRepository catalogRepository;
	
	
//	@SuppressWarnings("unused")
//	@Autowired
//	private DataSourceConfiguration dataSourceConfig;
//	
//	@Value("${urlBase}")
//	private String urlBase;
//	
//	@Autowired
////	@Qualifier("${dataSourceConfig.dao}")
//	@Resource(name = "CatalogDAO" + "${dao}")
//	CatalogDAO catalogDAO;
	
	PropertyList propertyList;

	/*
	public PropertyList addCatalogItem(PropertyList propertyList) {
		catalogDAO = new CatalogDAOHibernate();		
		
		CatalogItem catalog = new Catalog();
		catalog.setName((String)propertyList.mustProperty("NAME"));
		catalog.setDescription((String)propertyList.mustProperty("DESCRIPTION"));
		catalog.setDtCreated(new Date());
		catalog.setParent(null);

		propertyList.addProperty("ENTITY", catalog);
		catalogDAO.addCatalogItem(propertyList);

		return propertyList;
	}
	*/
	
//	public PropertyList addCatalogItem(PropertyList propertyList) {
////		catalogDAO = new CatalogDAOHibernate();		
//		
////		CatalogItem catalog = new Catalog();
//		Catalog catalog = new Catalog();
//
//		catalog.setName((String)propertyList.mustProperty("NAME"));
//		catalog.setDescription((String)propertyList.mustProperty("DESCRIPTION"));
//		catalog.setDtCreated(new Date());
//		catalog.setParent(null);
//
////		propertyList.addProperty("ENTITY", catalog);
////		catalogDAO.addCatalogItem(propertyList);
//		
//		Optional<Catalog> hasCatalog = catalogRepository.findById(catalog.getId());
//
//		if(!hasCatalog.isEmpty()) {
//			propertyList.addProperty("DUP_KEY_EXCEPTION", "DUP KEY");
//			return propertyList;
//		}
//		
//		CatalogItem savedCatalog = catalogRepository.save(catalog);
//		propertyList.addProperty("ENTITY", savedCatalog);
//		return propertyList;
//	}
	
	public Catalog create(Catalog catalog) {
		Catalog savedCatalog = catalogRepository.save(catalog);
		return savedCatalog;
	}
	
	public List<Catalog> listCatalogs(){
		List<Catalog> itens = catalogRepository.findAll();
		return itens;
	}
	
	public List<Catalog> listAllCatalogs(){
		List<Catalog> itens = catalogRepository.findAll();
		return itens;
	}
	
	public Catalog findById(String id) {
		Optional<Catalog> hasCatalog = catalogRepository.findById(id);
		
		return hasCatalog.orElse(null);

//		if(!hasCatalog.isEmpty()) {
//			propertyList.addProperty("DUP_KEY_EXCEPTION", "DUP KEY");
//			return propertyList;
//		}
	}
}