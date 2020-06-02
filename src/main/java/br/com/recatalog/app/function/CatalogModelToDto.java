package br.com.recatalog.app.function;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import br.com.recatalog.app.dto.CatalogDTO;
import br.com.recatalog.app.dto.PropertyCatalogDTO;
import br.com.recatalog.app.model.domain.Catalog;

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