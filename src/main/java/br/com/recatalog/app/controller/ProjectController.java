package br.com.recatalog.app.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.recatalog.app.Exception.DuplicatedCatalogItemException;
import br.com.recatalog.app.Exception.GlobalExceptionController;
import br.com.recatalog.app.Exception.ParentCatalogItemNotFoundException;
import br.com.recatalog.app.model.domain.Project;
import br.com.recatalog.app.service.ProjectService;
import br.com.recatalog.app.util.BreadCrumbSession;
import br.com.recatalog.util.PropertyList;

@Controller
@RequestMapping("recatalog")
public class ProjectController {
	
	private Logger logger = LoggerFactory.getLogger(GlobalExceptionController.class);
	
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
			breadCrumbSession.clearProject();
		} else {
			mav.addObject("msg", "success");
			
			Project createdProject = (Project) propertyList.getProperty("ENTITY");
			breadCrumbSession.clearCatalog();
			breadCrumbSession.setCatalogName(catalogName);
			breadCrumbSession.setProjectName(projectName);
		}
		return mav;
	}

	@GetMapping("/projects")
	public String listCatalogs(Model model) {
		model.addAttribute("projects", projectService.listAllProjects());
		return "project.html";
	}
	
	@ExceptionHandler(IllegalStateException.class)
	public String repositoryExistsException(IllegalStateException e, HttpServletRequest request, RedirectAttributes redirectAttrs){

		StringWriter exceptionStackError = new StringWriter();
		e.printStackTrace(new PrintWriter(exceptionStackError));
		
		logger.error("ERROR ON URL: " + request.getRequestURL());
		logger.error( exceptionStackError.toString());
		
		redirectAttrs.addFlashAttribute("error", e);
	    redirectAttrs.addFlashAttribute("msg", "error");
	    
	    return "redirect:project.html";
	}
	
	@ExceptionHandler(DuplicatedCatalogItemException.class)
	public String duplicatedCatalogItemException(Exception e, HttpServletRequest request, RedirectAttributes redirectAttrs){

		StringWriter exceptionStackError = new StringWriter();
		e.printStackTrace(new PrintWriter(exceptionStackError));
		
		logger.error("ERROR ON URL: " + request.getRequestURL());
		logger.error( exceptionStackError.toString());
		
		redirectAttrs.addFlashAttribute("error", e);
	    redirectAttrs.addFlashAttribute("msg", "error");
	    
	    return "redirect:project.html";
	}
	
	@ExceptionHandler(ParentCatalogItemNotFoundException.class)
	public String parentCatalogItemNotFoundException(Exception e, HttpServletRequest request, RedirectAttributes redirectAttrs){

		StringWriter exceptionStackError = new StringWriter();
		e.printStackTrace(new PrintWriter(exceptionStackError));
		
		logger.error("ERROR ON URL: " + request.getRequestURL());
		logger.error( exceptionStackError.toString());
		
		redirectAttrs.addFlashAttribute("error", e);
	    redirectAttrs.addFlashAttribute("msg", "error");
	    
	    return "redirect:project.html";
	}
	
	@ExceptionHandler(Exception.class)
	public String generalException(Exception e, HttpServletRequest request, RedirectAttributes redirectAttrs){

		StringWriter exceptionStackError = new StringWriter();
		e.printStackTrace(new PrintWriter(exceptionStackError));
		
		logger.error("ERROR ON URL: " + request.getRequestURL());
		logger.error( exceptionStackError.toString());
		
		redirectAttrs.addFlashAttribute("error", e);
	    redirectAttrs.addFlashAttribute("msg", "error");
	    
	    return "redirect:project.html";
	}	
}