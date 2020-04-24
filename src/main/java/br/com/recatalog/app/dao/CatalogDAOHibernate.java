package br.com.recatalog.app.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.recatalog.app.model.domain.Catalog;
import br.com.recatalog.app.model.domain.CatalogItem;
import br.com.recatalog.app.model.domain.PropertyCatalog;
import br.com.recatalog.app.model.domain.SourceRepository;
import br.com.recatalog.util.PropertyList;

@Component("CatalogDAOHibernate")
//@Qualifier("hibernate")
public class CatalogDAOHibernate implements CatalogDAO {
	
	@Override
	public PropertyList getCatalogById(PropertyList properties) {
		
		EntityManagerFactory ENTITY_MANAGER_FACTORY =
				Persistence.createEntityManagerFactory("PU-DBRECATALOG");	
		
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		
		String id = (String)properties.getProperty("ID");

		Catalog catalog = em.find(Catalog.class, id);
		
		properties.addProperty("ENTITY", catalog);
		
		em.close();
		ENTITY_MANAGER_FACTORY.close();		
		
		return properties;
	}

	@Override
	public PropertyList getCataLogByParentId(PropertyList props) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public PropertyList addCatalog(PropertyList properties) {
//		
//		EntityManagerFactory ENTITY_MANAGER_FACTORY =
//				Persistence.createEntityManagerFactory("PU-DBRECATALOG");
//		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
//		
//		CatalogOld catalog = (CatalogOld) properties.getProperty("ENTITY");
//
//		em.getTransaction().begin();
//		em.persist(catalog);
//		
//		System.err.println("SIZE: " + catalog.getProperties().size());
//		
//		for(PropertyCatalogOld pc : catalog.getProperties()) {
//			em.persist(pc);
//		}
//		em.getTransaction().commit();
//		System.out.println(catalog.getId());
//		
//		em.close();
//		
//		System.err.println(catalog.getId() + " " + catalog.getName());
//		
//		ENTITY_MANAGER_FACTORY.close();		
//
//		return null;
//	}

	@Override
	public PropertyList getCatalogAll(PropertyList props) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PropertyList addCatalogItem(PropertyList properties) {
		EntityManagerFactory ENTITY_MANAGER_FACTORY =
				Persistence.createEntityManagerFactory("PU-DBRECATALOG");
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		
		CatalogItem catalog = (CatalogItem) properties.getProperty("ENTITY");

		em.getTransaction().begin();
		em.persist(catalog);
		
		System.err.println("SIZE: " + catalog.getProperties().size());
		
		for(PropertyCatalog pc : catalog.getProperties()) {
			em.persist(pc);
		}
		em.getTransaction().commit();
		System.out.println(catalog.getId());
		
		em.close();
		
		System.err.println(catalog.getId() + " " + catalog.getName());
		
		ENTITY_MANAGER_FACTORY.close();		

		return properties;

	}
	
	@Override
	public PropertyList addSourceRepository(PropertyList properties) {
		
		EntityManagerFactory ENTITY_MANAGER_FACTORY =
				Persistence.createEntityManagerFactory("PU-DBRECATALOG");
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		
		SourceRepository catalog = (SourceRepository) properties.getProperty("ENTITY");

		em.getTransaction().begin();
		em.persist(catalog);
		
		System.err.println("SIZE: " + catalog.getProperties().size());
		
		for(PropertyCatalog pc : catalog.getProperties()) {
			em.persist(pc);
		}
		em.getTransaction().commit();
		System.out.println(catalog.getId());
		
		em.close();
		
		System.err.println(catalog.getId() + " " + catalog.getName());
		
		ENTITY_MANAGER_FACTORY.close();		

		return null;
	}
}