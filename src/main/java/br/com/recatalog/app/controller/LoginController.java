package br.com.recatalog.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
	@PostMapping("login")
	public String login() {
		return "index.html";
	}	
	
	@GetMapping("login")
	public String loginx() {
		return "index.html";
	}
}