package br.com.recatalog.app.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.recatalog.app.dao.CatalogDAO;
import br.com.recatalog.app.dao.CatalogDAOHibernate;
import br.com.recatalog.app.domain.repository.CatalogItemRepository;
import br.com.recatalog.app.model.domain.Catalog;
import br.com.recatalog.app.model.domain.CatalogItem;
import br.com.recatalog.util.PropertyList;

@Service
public class CatalogItemService {
	
	@Autowired
	CatalogItemRepository catalogItemRepository;
	
	
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
	
	public PropertyList addCatalogItem(PropertyList propertyList) {
//		catalogDAO = new CatalogDAOHibernate();		
		
//		CatalogItem catalog = new Catalog();
		Catalog catalog = new Catalog();

		catalog.setName((String)propertyList.mustProperty("NAME"));
		catalog.setDescription((String)propertyList.mustProperty("DESCRIPTION"));
		catalog.setDtCreated(new Date());
		catalog.setParent(null);

//		propertyList.addProperty("ENTITY", catalog);
//		catalogDAO.addCatalogItem(propertyList);
		
		Optional<CatalogItem> hasCatalog = catalogItemRepository.findById(catalog.getId());

		if(!hasCatalog.isEmpty()) {
			propertyList.addProperty("EXCEPTION", "DUP KEY");
			return propertyList;
		}
		
		CatalogItem savedCatalog = catalogItemRepository.save(catalog);
		propertyList.addProperty("ENTITY", savedCatalog);
		return propertyList;
	}
	
	public Catalog create(Catalog catalog) {
		Catalog savedCatalog = catalogItemRepository.save(catalog);
		return savedCatalog;
	}
	
	public List<CatalogItem> listCatalogItens(){
		List<CatalogItem> itens = catalogItemRepository.findAll();
		return itens;
	}
	
	public List<CatalogItem> listAllCatalogItens(){
		List<CatalogItem> itens = catalogItemRepository.findAll();
		return itens;
	}
}