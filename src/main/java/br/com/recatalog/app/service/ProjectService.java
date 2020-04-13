package br.com.recatalog.app.service;

import java.io.IOException;

import org.eclipse.jgit.lib.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.recatalog.app.configuration.DataSourceConfiguration;
import br.com.recatalog.app.configuration.GitConfiguration;
import br.com.recatalog.app.dao.ProjectDAO;
import br.com.recatalog.app.model.CatalogItem;
import br.com.recatalog.app.model.SourceRepository;
import br.com.recatalog.util.GitSourceManagement;
import br.com.recatalog.util.PropertyList;

@Service
public class ProjectService {
	
	@SuppressWarnings("unused")
	@Autowired
	private GitConfiguration gitConfig;
	
	@SuppressWarnings("unused")
	@Autowired
	private DataSourceConfiguration dataSourceConfig;
	
	@Autowired
	private CatalogService catalogService;
	
	@Autowired
//	@Qualifier("${dataSourceConfig.dao}")
	@Qualifier("ProjectDAOHibernate")

	private ProjectDAO projectDAO;
	
	public void addProject(PropertyList properties) throws IOException {
		
		String projectName = (String)properties.mustProperty("PROJECT_NAME");
		String projectDesc = (String)properties.mustProperty("PROJECT_DESC");
		String catalogName = (String)properties.mustProperty("CATALOG_NAME");
		

		// todos os nomes do catálogo  são case-sensitive
		projectName = projectName.toUpperCase();
		catalogName = catalogName.toUpperCase();
		
/* 		gitConfig.getUrlBase() == "${gitConfig.url}"
		String repoDir = gitConfig.getUrlBase() + System.getProperty("file.separator") + catalogName
		       + System.getProperty("file.separator") + projectName;
*/		
		String repoDir = "${gitConfig.url}" + System.getProperty("file.separator") + catalogName
			       + System.getProperty("file.separator") + projectName;
		
		Repository repo = GitSourceManagement.init(repoDir);
		
		System.out.println(repo.getDirectory().getCanonicalPath());
		
		/*
		 * String catalogId = catalogName + "." + "REPOSITORY" + projectName;
		 * 
		 * PropertyList propertyList = new PropertyList();
		 * propertyList.addProperty("ID", catalogId); propertyList.addProperty("NAME",
		 * projectName); propertyList.addProperty("DESCRIPTION", projectDesc);
		 * 
		 * CatalogItem repository = new SourceRepository();
		 * 
		 * repository.setName(projectName); repository.setParent(parent);
		 * 
		 * catalogService.addCatalogItem(propertyList);
		 */
	}
}