package br.com.recatalog.app.resource;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.recatalog.app.dto.CatalogDTO;
import br.com.recatalog.app.dto.PropertyCatalogDTO;
import br.com.recatalog.app.model.domain.Catalog;
import br.com.recatalog.app.service.CatalogService;

@RestController
@RequestMapping("api")
public class CatalogResource {
	
	@Autowired
	CatalogService catalogService;
	
	@GetMapping("catalogs")
	public List<CatalogDTO> catalogs() {
		
		/*
		 * Classes DTO criada para transferência para a UI.
		 * 
		 *  IMPORTANTE: ERRO ocorreu na deserialização do Catalog para JSON utilizando
		 *  Jackson. Ocorria loop na desserialização da properiedades
		 *  
		 *  @JsonIgnore // evita loop infinito, ref.: https://stackoverflow.com/questions/3325387/infinite-recursion-with-jackson-json-and-hibernate-jpa-issue
		 * 	@JsonBackReference // ref.: https://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion
		 * 
		 */		

		//defina aqui ou defina depois
		Function<Catalog, CatalogDTO> toDto = new Function<Catalog, CatalogDTO>() {
			@Override
			public CatalogDTO apply(Catalog catModel) {
		    	CatalogDTO catDTO = new CatalogDTO();
		    	
		    	catDTO.setId(catModel.getId());
		    	catDTO.setCreatedOn(catModel.getCreatedOn());
		    	catDTO.setDescription(catModel.getDescription());
		    	catDTO.setName(catModel.getName());
		    	
		    	if(catModel.getParent() == null) {
		    		catDTO.setParentId(null);
		    	} else {
		    		catDTO.setParentId(catModel.getParent().getId());
		    	}
		    	
		    	List<PropertyCatalogDTO> propertiesDTO = catModel.getProperties()
							.stream()
							.map(prop -> {
								PropertyCatalogDTO pdto = new PropertyCatalogDTO();
								pdto.setKey(prop.getKey());
								pdto.setValue(prop.getValue());
								return pdto;
								})
							.collect(Collectors.toList());
		    	
		    	catDTO.setProperties(propertiesDTO);
		        return catDTO;
		    }
		};
		
		List<Catalog> catalogs = catalogService.listAllCatalogs();

//		CatalogModelToDto toDto = new CatalogModelToDto();
		
		List<CatalogDTO> catalogsDto = catalogs.stream().map(toDto).collect(Collectors.toList());
		
		return catalogsDto;
	}

	// ou defina aqui e instancia como na linha 61
	public class CatalogModelToDto implements Function<Catalog, CatalogDTO> {
	    @Override
	    public CatalogDTO apply(Catalog catModel) {
	    	CatalogDTO catDTO = new CatalogDTO();
	    	
	    	catDTO.setId(catModel.getId());
	    	catDTO.setCreatedOn(catModel.getCreatedOn());
	    	catDTO.setDescription(catModel.getDescription());
	    	catDTO.setName(catModel.getName());
	    	
	    	if(catModel.getParent() == null) {
	    		catDTO.setParentId(null);
	    	} else {
	    		catDTO.setParentId(catModel.getParent().getId());
	    	}
	    	
	    	List<PropertyCatalogDTO> propertiesDTO = catModel.getProperties()
						.stream()
						.map(prop -> {
							PropertyCatalogDTO pdto = new PropertyCatalogDTO();
							pdto.setKey(prop.getKey());
							pdto.setValue(prop.getValue());
							return pdto;
							})
						.collect(Collectors.toList());
	    	
	    	catDTO.setProperties(propertiesDTO);
	        return catDTO;
	    }
	}
}