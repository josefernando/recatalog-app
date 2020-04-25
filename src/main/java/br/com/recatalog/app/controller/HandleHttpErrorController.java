package br.com.recatalog.app.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.recatalog.app.Exception.GlobalExceptionController;

@Controller
@RequestMapping(value="/")
public class HandleHttpErrorController implements ErrorController{
	
	private Logger logger = LoggerFactory.getLogger(GlobalExceptionController.class);


private static final String PATH = "/error";

	@GetMapping(value=PATH)
	    public ModelAndView handleError(HttpServletRequest request, HttpServletResponse response) {
	
		logger.error("[handleError] ERROR ON URL: " + request.getRequestURL()); // is always "<server>/error"
		logger.error("[handleError] ERROR ON URL: " + request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI));

		ModelAndView mav = new ModelAndView();
		mav.setViewName("error");
	    mav.addObject("msg", request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI) + " " + response.getStatus());

	    return mav;
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}

