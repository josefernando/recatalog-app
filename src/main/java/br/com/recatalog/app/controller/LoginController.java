package br.com.recatalog.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.recatalog.app.util.BreadCrumbSession;

@Controller
public class LoginController {
	
	
	@Autowired
	private BreadCrumbSession breadCrumbSession;
	
	@PostMapping("login")
	public String login() {
		breadCrumbSession.clearCatalog();
		return "login.html";
	}	
	
	/*
	 * No caso de falha do login, automaticamente será chamado o request GET "/login"
	 *  Ref.: https://docs.spring.io/spring-security/site/docs/current/reference/pdf/spring-security-reference.pdf
	 *  pag. 62
	 */
	
	@GetMapping("login")
	public ModelAndView loginx() {
		breadCrumbSession.clearCatalog();

		ModelAndView mav = new ModelAndView();
		mav.setViewName("login.html");
		mav.addObject("login", true);
		return mav;
	}
}