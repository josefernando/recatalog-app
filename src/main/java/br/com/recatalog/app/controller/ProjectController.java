package br.com.recatalog.app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.recatalog.app.model.Project;
import br.com.recatalog.app.service.ProjectService;
import br.com.recatalog.app.util.BreadCrumbSession;
import br.com.recatalog.util.PropertyList;

@Controller
@RequestMapping("recatalog")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private BreadCrumbSession breadCrumbSession;
		
	@PostMapping("/projects")
	public ModelAndView createProject( @RequestParam String catalogName
			,@RequestParam(name = "projectName") String projectName
			,@RequestParam String description) throws IOException {
		
		PropertyList propertyList = new PropertyList();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("project.html");
		
		List validateView = new ArrayList<String>();
		
		if (catalogName.isEmpty()) {
			validateView.add("Informe Nome do Catálogo");
		}
		
		if (projectName.isEmpty()) {
			validateView.add("Informe Nome do Projeto");
		}
		
		if (description.isEmpty()) {
			validateView.add("Informe Descrição do Projeto");
		}
		
		if(!validateView.isEmpty()) {
			mav.addObject("validateView", validateView);
			return mav;
		}

		propertyList.addProperty("PROJECT_NAME", projectName);
		propertyList.addProperty("CATALOG_NAME", catalogName);
		propertyList.addProperty("PROJECT_DESC", description);

		projectService.createProject(propertyList);

		if (propertyList.hasProperty("EXCEPTION")) {
			mav.addObject("msg", "error");
		} else {
			mav.addObject("msg", "success");
			
			Project createdProject = (Project) propertyList.getProperty("ENTITY");
			breadCrumbSession.clearCatalog();
			breadCrumbSession.setCatalogName(catalogName);
			breadCrumbSession.setProjectName(projectName);
		}
		return mav;
	}
}