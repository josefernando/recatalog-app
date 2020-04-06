package br.com.recatalog.app.Exception;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ControllerAdvice
@EnableWebMvc
public class GlobalExceptionController {
	private Logger logger = LoggerFactory.getLogger(GlobalExceptionController.class);
	
	@ExceptionHandler(value = Exception.class)
	public String handlerError(HttpServletRequest req, Exception exception, Model model) {
		Exception e = null;
		for(Throwable t : findCauseUsingPlainJava(exception)) {
			e = (Exception) t;
			logger.error("Request: " + req.getRequestURL() + " raised " + e);
		}
//		logger.error("Request: " + req.getRequestURL() + " raised " + exception);
//		logger.error("Request2: " + req.getRequestURL() + " raised " + exception);

		model.addAttribute("msg", getMsg(e));
		return "error";
	}
	
	
	
	/*
	 * Find the root cause of the exception
	 * how-to: https://www.baeldung.com/java-exception-root-cause
	 */
	public static List<Throwable> findCauseUsingPlainJava(Throwable throwable) {
		List<Throwable> throwables = new ArrayList();
	    Objects.requireNonNull(throwable);
	    Throwable rootCause = throwable;
	    throwables.add(rootCause);
	    while (rootCause.getCause() != null && rootCause.getCause() != rootCause) {
	        rootCause = rootCause.getCause();
		    throwables.add(rootCause);
	    }
	    //return rootCause;
	    return throwables;
	}
	
	public String  getMsg(Throwable ex) {
		 if(SQLException.class.isInstance(ex)) {
			 SQLException sqle = (SQLException)ex;
			  return "error code: " + sqle.getErrorCode()
			         + " sql state: " + sqle.getSQLState()
			         + " sql state: " + sqle.getMessage();
		 }
		 else 
		 return ex.getMessage();
	}
	
	
	
	
}