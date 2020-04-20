package br.com.recatalog.app.service;

import java.io.IOException;

import org.eclipse.jgit.lib.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.recatalog.app.configuration.DataSourceConfiguration;
import br.com.recatalog.app.configuration.GitConfiguration;
import br.com.recatalog.app.repository.CatalogRepository;
import br.com.recatalog.util.GitSourceManagement;
import br.com.recatalog.util.PropertyList;

@Service
public class ProjectService {
	
	@SuppressWarnings("unused")
	@Autowired
	private GitConfiguration gitConfig;
	
	@Autowired
	CatalogRepository catalogRepository;
	
	@SuppressWarnings("unused")
	@Autowired
	private DataSourceConfiguration dataSourceConfig;
	
	@Value("${recatalog.git.urlbase}") // recupera valor do arquivo application.properties
	private String gitUrlBase;
	
	public void createProject(PropertyList properties) throws IOException {
		String projectName = (String)properties.mustProperty("PROJECT_NAME");
		String projectDesc = (String)properties.mustProperty("PROJECT_DESC");
		String catalogName = (String)properties.mustProperty("CATALOG_NAME");

		// todos os nomes do catálogo  são case-sensitive
		projectName = projectName.toUpperCase();
		catalogName = catalogName.toUpperCase();
		
/* 		gitConfig.getUrlBase() == "${gitConfig.url}"
 *               ou
		String repoDir = gitConfig.getUrlBase() + System.getProperty("file.separator") + catalogName
		       + System.getProperty("file.separator") + projectName;
*/		
		
		String repoDir = gitUrlBase + System.getProperty("file.separator") + catalogName
			       + System.getProperty("file.separator") + projectName;
		
		
		// throw IllegalStateException para repositórios já existentes
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