package br.com.recatalog.app.dto;

import java.util.Date;
import java.util.List;


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

public abstract class CatalogItemDTO {

	protected String id;
	private Date createdOn;
    private String name;
    private String description;
	private String parentId;
	private List<PropertyCatalogDTO> properties;
	
	public CatalogItemDTO() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public List<PropertyCatalogDTO> getProperties() {
		return properties;
	}

	public void setProperties(List<PropertyCatalogDTO> properties) {
		this.properties = properties;
	}
}