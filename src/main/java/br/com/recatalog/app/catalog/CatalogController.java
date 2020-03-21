package br.com.recatalog.app.catalog;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@RestController //-> for restfull api
@Controller   // for html response
public class CatalogController {
	@RequestMapping( "/recatalog") // , produces = MediaType.TEXT_HTML_VALUE)
//	@ResponseBody
	public String home() {
		return "home.jsp";
	}

}
