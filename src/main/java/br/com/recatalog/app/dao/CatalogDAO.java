package br.com.recatalog.app.dao;

import br.com.recatalog.util.PropertyList;

public interface CatalogDAO {
	public PropertyList getCatalogById(PropertyList props);
	
	public PropertyList getCataLogByParentId(PropertyList props);
	
	//public PropertyList addCatalog(PropertyList properties);
	
	public PropertyList addCatalogItem(PropertyList properties);
	
	public PropertyList addSourceRepository(PropertyList properties);



	public PropertyList getCatalogAll(PropertyList props);
}