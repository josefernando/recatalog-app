package br.com.recatalog.app.service;

import java.util.Date;

import br.com.recatalog.app.dao.CatalogDAO;
import br.com.recatalog.app.dao.CatalogDAOHibernate;
import br.com.recatalog.app.model.Catalog;
import br.com.recatalog.app.model.CatalogItem;
import br.com.recatalog.app.model.PropertyCatalog;
import br.com.recatalog.util.PropertyList;

public class CatalogService {
	
	CatalogDAO catalogDAO;
	PropertyList propertyList;
	
	public PropertyList addCatalogItem(PropertyList propertyList) {
		catalogDAO = new CatalogDAOHibernate();		
		
		CatalogItem catalog = new Catalog();
//		catalog.setId((String)propertyList.mustProperty("ID"));
		catalog.setName((String)propertyList.mustProperty("NAME"));
		catalog.setDescription((String)propertyList.mustProperty("DESCRIPTION"));
		catalog.setDtCreated(new Date());
		catalog.setParent(null);
		
//		PropertyCatalog pc = new PropertyCatalog(catalog,"KEY_PROPERTY", "VALUE_PROPERTY");
//		catalog.addProperty(pc);

		propertyList.addProperty("ENTITY", catalog);
		catalogDAO.addCatalogItem(propertyList);

		return propertyList;
	}
}
