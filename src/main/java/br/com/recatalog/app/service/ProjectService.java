package br.com.recatalog.app.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.recatalog.app.Exception.DuplicatedCatalogItemException;
import br.com.recatalog.app.Exception.ParentCatalogItemNotFoundException;
import br.com.recatalog.app.domain.repository.CatalogItemRepository;
import br.com.recatalog.app.domain.repository.ProjectRepository;
import br.com.recatalog.app.model.domain.CatalogItem;
import br.com.recatalog.app.model.domain.Project;
import br.com.recatalog.util.PropertyList;

@Service
public class ProjectService {
	
	@Autowired
	CatalogItemRepository catalogItemRepository;
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	public PropertyList createProject(PropertyList properties) throws IOException {
		String projectName = (String)properties.mustProperty("PROJECT_NAME");
		String projectDesc = (String)properties.mustProperty("PROJECT_DESC");
		String catalogName = (String)properties.mustProperty("CATALOG_NAME");

 // todos os nomes do catálogo  são case-sensitive
		projectName = projectName.toUpperCase();
		catalogName = catalogName.toUpperCase();
		
	    String CREATE_EMPLOYEE_ENDPOINT_URL = "http://STORAGE-GIT-SERVICE/api/git/repositories";
	    
//        RestTemplate restTemplate = new RestTemplate();
        
        ResponseEntity<String> entity = restTemplate.postForEntity(CREATE_EMPLOYEE_ENDPOINT_URL, projectName, String.class);
		
		Project project = new Project();

		project.setName(projectName);
		project.setDescription(projectDesc);
		project.setDtCreated(new Date());
		
		Optional<CatalogItem> catalogParent = catalogItemRepository.findById(catalogName);
		
		if(catalogParent.isEmpty()) {
			throw new ParentCatalogItemNotFoundException("Parent Catalog Not Found:" + catalogName);
		}
		
		CatalogItem parent = catalogParent.get();
		project.setParent(parent);
		
		Optional<CatalogItem> existProject = catalogItemRepository.findById(project.getId());

		if(!existProject.isEmpty()) {
			throw new DuplicatedCatalogItemException("Projeto Id Duplicado:" + project.getId());
		}
		
		CatalogItem savedProject = catalogItemRepository.save(project);
		properties.addProperty("ENTITY", savedProject);
		return properties;
	}
	
	public List<Project> listAllProjects(){
		List<Project> projects = projectRepository.findAll();
		return projects;
	}

}