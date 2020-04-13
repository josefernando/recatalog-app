package br.com.recatalog.app.Exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
//@EnableWebMvc

/*
 * ref: https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc#sample-application
 */
public class GlobalExceptionController {
	private Logger logger = LoggerFactory.getLogger(GlobalExceptionController.class);
	
	@ExceptionHandler(value = Exception.class)
	public ModelAndView handlerError(HttpServletRequest req, Exception exception) {
		Exception e = null;
		for(Throwable t : findCauseUsingPlainJava(exception)) {
			e = (Exception) t;
			logger.error("Request: " + req.getRequestURL() + " raised " + e);
		}
		
		String msg = getMsg(e); 
		
		StringWriter exceptionStackError = new StringWriter();
		e.printStackTrace(new PrintWriter(exceptionStackError));
		
		logger.error( exceptionStackError.toString());

		
		msg = msg != null ? msg : e.toString();
		
		ModelAndView mav = new ModelAndView();

		mav.setViewName("error");
//		mav.addObject("msg", getMsg(e));
		mav.addObject("msg", msg);

		return mav;
	}	
	
	/*
	 * Find the root cause of the exception
	 * how-to: https://www.baeldung.com/java-exception-root-cause
	 */
	public  List<Throwable> findCauseUsingPlainJava(Throwable throwable) {
		List<Throwable> throwables = new ArrayList();
	    Objects.requireNonNull(throwable);
	    Throwable rootCause = throwable;
	    throwables.add(rootCause);
	    while (rootCause.getCause() != null && rootCause.getCause() != rootCause) {
	        rootCause = rootCause.getCause();
		    throwables.add(rootCause);
	    }
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