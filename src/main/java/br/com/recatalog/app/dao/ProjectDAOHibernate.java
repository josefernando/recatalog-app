package br.com.recatalog.app.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.recatalog.app.model.domain.Project;
import br.com.recatalog.util.PropertyList;

@Component("ProjectDAOHibernate")
public class ProjectDAOHibernate implements CatalogItemDAO, ProjectDAO {

	@Override
	public PropertyList getById(PropertyList properties) {
		
		EntityManagerFactory ENTITY_MANAGER_FACTORY =
				Persistence.createEntityManagerFactory("PU-DBRECATALOG");	
		
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		
		String id = (String)properties.getProperty("ID");

		Project project = em.find(Project.class, id);
		
		properties.addProperty("ENTITY", project);
		
		em.close();
		ENTITY_MANAGER_FACTORY.close();		
		return properties;
	}
}