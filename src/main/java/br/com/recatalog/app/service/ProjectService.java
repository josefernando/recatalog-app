package br.com.recatalog.app.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.eclipse.jgit.lib.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.recatalog.app.Exception.DuplicatedCatalogItemException;
import br.com.recatalog.app.Exception.ParentCatalogItemNotFoundException;
import br.com.recatalog.app.configuration.GitConfiguration;
import br.com.recatalog.app.domain.repository.CatalogRepository;
import br.com.recatalog.app.domain.repository.ProjectRepository;
import br.com.recatalog.app.model.domain.CatalogItem;
import br.com.recatalog.app.model.domain.Project;
import br.com.recatalog.util.GitSourceManagement;
import br.com.recatalog.util.PropertyList;

@Service
public class ProjectService {
	
	@SuppressWarnings("unused")
	@Autowired
	private GitConfiguration gitConfig;
	
	@Autowired
	CatalogRepository catalogRepository;
	
	@Autowired
	ProjectRepository projectRepository;
	
//	@SuppressWarnings("unused")
//	@Autowired
//	private DataSourceConfiguration dataSourceConfig;
	
	@Value("${recatalog.git.urlbase}") // recupera valor do arquivo application.properties
	private String gitUrlBase;
	
	public PropertyList createProject(PropertyList properties) throws IOException {
		String projectName = (String)properties.mustProperty("PROJECT_NAME");
		String projectDesc = (String)properties.mustProperty("PROJECT_DESC");
		String catalogName = (String)properties.mustProperty("CATALOG_NAME");

		// todos os nomes do catálogo  são case-sensitive
		projectName = projectName.toUpperCase();
		catalogName = catalogName.toUpperCase();
		
//===========================================================
		Project project = new Project();

		project.setName(projectName);
		project.setDescription(projectDesc);
		project.setDtCreated(new Date());
		
		Optional<CatalogItem> catalogParent = catalogRepository.findById(catalogName);
		
		if(catalogParent.isEmpty()) {
			throw new ParentCatalogItemNotFoundException("Parent Catalog Not Found:" + catalogName);
		}
		
		CatalogItem parent = catalogParent.get();
		project.setParent(parent);
		
		Optional<CatalogItem> hasCatalog = catalogRepository.findById(project.getId());

		if(!hasCatalog.isEmpty()) {
			throw new DuplicatedCatalogItemException("Projeto Id Duplicado:" + project.getId());
		}
//===========================================================
		
		
		
		
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
		
//		Project project = new Project();
//
//		project.setName(projectName);
//		project.setDescription(projectDesc);
//		project.setDtCreated(new Date());
//		
//		Optional<CatalogItem> catalogParent = catalogRepository.findById(catalogName);
//		
//		if(catalogParent.isEmpty()) {
//			throw new ParentCatalogItemNotFoundException("Parent Catalog Not Found:" + catalogName);
//		}	
		
//		CatalogItem parent = catalogParent.get();
//		project.setParent(parent);
//
//		
//		Optional<CatalogItem> hasCatalog = catalogRepository.findById(project.getId());
//
//		if(!hasCatalog.isEmpty()) {
//			throw new DuplicatedCatalogItemException("Projeto Id Duplicado:" + project.getId());
//		}
		
		CatalogItem savedProject = catalogRepository.save(project);
		properties.addProperty("ENTITY", savedProject);
		return properties;
	}
	
	public PropertyList addCatalogItem(PropertyList propertyList) {
		Project project = new Project();

		project.setName((String)propertyList.mustProperty("NAME"));
		project.setDescription((String)propertyList.mustProperty("DESCRIPTION"));
		project.setDtCreated(new Date());
		project.setParent((CatalogItem)propertyList.mustProperty("PARENT"));
		
		Optional<CatalogItem> hasCatalog = catalogRepository.findById(project.getId());

		if(!hasCatalog.isEmpty()) {
			throw new DuplicatedCatalogItemException("Projeto Id Duplicado:" + project.getId());
		}
		
		CatalogItem savedProject = catalogRepository.save(project);
		propertyList.addProperty("ENTITY", savedProject);
		return propertyList;
	}
	
	public List<Project> listAllProjects(){
		List<Project> projects = projectRepository.findAll();
		return projects;
	}
}