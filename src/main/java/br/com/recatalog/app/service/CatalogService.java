package br.com.recatalog.app.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.recatalog.app.domain.repository.CatalogRepository;
import br.com.recatalog.app.dto.CatalogDTO;
import br.com.recatalog.app.dto.PropertyCatalogDTO;
import br.com.recatalog.app.function.CatalogModelToDto;
import br.com.recatalog.app.model.domain.Catalog;
import br.com.recatalog.util.PropertyList;

@Service
public class CatalogService {
	
	@Autowired
	CatalogRepository catalogRepository;
	
	
//	@SuppressWarnings("unused")
//	@Autowired
//	private DataSourceConfiguration dataSourceConfig;
//	
//	@Value("${urlBase}")
//	private String urlBase;
//	
//	@Autowired
////	@Qualifier("${dataSourceConfig.dao}")
//	@Resource(name = "CatalogDAO" + "${dao}")
//	CatalogDAO catalogDAO;
	
	PropertyList propertyList;

	/*
	public PropertyList addCatalogItem(PropertyList propertyList) {
		catalogDAO = new CatalogDAOHibernate();		
		
		CatalogItem catalog = new Catalog();
		catalog.setName((String)propertyList.mustProperty("NAME"));
		catalog.setDescription((String)propertyList.mustProperty("DESCRIPTION"));
		catalog.setDtCreated(new Date());
		catalog.setParent(null);

		propertyList.addProperty("ENTITY", catalog);
		catalogDAO.addCatalogItem(propertyList);

		return propertyList;
	}
	*/
	
//	public PropertyList addCatalogItem(PropertyList propertyList) {
////		catalogDAO = new CatalogDAOHibernate();		
//		
////		CatalogItem catalog = new Catalog();
//		Catalog catalog = new Catalog();
//
//		catalog.setName((String)propertyList.mustProperty("NAME"));
//		catalog.setDescription((String)propertyList.mustProperty("DESCRIPTION"));
//		catalog.setDtCreated(new Date());
//		catalog.setParent(null);
//
////		propertyList.addProperty("ENTITY", catalog);
////		catalogDAO.addCatalogItem(propertyList);
//		
//		Optional<Catalog> hasCatalog = catalogRepository.findById(catalog.getId());
//
//		if(!hasCatalog.isEmpty()) {
//			propertyList.addProperty("DUP_KEY_EXCEPTION", "DUP KEY");
//			return propertyList;
//		}
//		
//		CatalogItem savedCatalog = catalogRepository.save(catalog);
//		propertyList.addProperty("ENTITY", savedCatalog);
//		return propertyList;
//	}
	
	public Catalog create(Catalog catalog) {
		Catalog savedCatalog = catalogRepository.save(catalog);
		return savedCatalog;
	}
	
	public List<CatalogDTO> listCatalogs(){
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

		CatalogModelToDto modelToDto = new CatalogModelToDto();
		
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
							.map(propDTO -> {
								PropertyCatalogDTO pdto = new PropertyCatalogDTO();
								pdto.setKey(propDTO.getKey());
								pdto.setValue(propDTO.getValue());
								return pdto;
								})
							.collect(Collectors.toList());
		    	
		    	catDTO.setProperties(propertiesDTO);
		        return catDTO;
		    }
		};		
		
		List<Catalog> catalogs = catalogRepository.findAll();
		
//		List<CatalogDTO> catalogsDtox = catalogs.stream().map(modelToDto).collect(Collectors.toList());

		
		List<CatalogDTO> catalogsDto = catalogs.stream().map(toDto).collect(Collectors.toList());
		
		return catalogsDto;		
	}
	
	public List<Catalog> listAllCatalogs(){
		List<Catalog> itens = catalogRepository.findAll();
		return itens;
	}
	
	public Catalog findById(String id) {
		Optional<Catalog> hasCatalog = catalogRepository.findById(id);
		
		return hasCatalog.orElse(null);
	}
}