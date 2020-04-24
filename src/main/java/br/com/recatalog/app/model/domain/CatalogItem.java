package br.com.recatalog.app.model.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.data.domain.Persistable;

@Entity
@Table(name = "TBCATALOG_ITEM")
@Inheritance(strategy = InheritanceType.JOINED)
public class CatalogItem implements Serializable, Persistable{
	private static final long serialVersionUID = 1L;
	
	public CatalogItem() {
		properties = new ArrayList<PropertyCatalog>();
	}

	@Id
	@Column(name="ID", unique = true)
	protected String id;

	@Column(name="CREATED_ON", nullable=false)
	private Date createdOn;
	
	@Column(name="NAME", nullable=false)
	@NotBlank
    private String name;
    
	@Column(name="DESCRIPTION", nullable=false)
	@NotBlank
    private String description;
    
	@OneToOne()  // foreign key  
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
	private CatalogItem parent;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy="propertyId.catalog")
	private List<PropertyCatalog> properties;
    
	public String getId() {
		return id ; 
	}
	
	private void setId(String id) {
		if(getParent() != null) {
			this.id = getParent().getId().concat(".").concat(id);
		}
		else this.id = id;
	}
	
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setDtCreated(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		setId(name);
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public CatalogItem getParent() {
		return parent;
	}
	
	public void setParent(CatalogItem parent) {
		this.parent = parent;
		
		if(getId() != null && getParent() != null) {
			this.id = getParent().getId().concat(".").concat(getId());
		}
	}

	public List<PropertyCatalog> getProperties() {
		return properties;
	}
	public void setProperties(List<PropertyCatalog> properties) {
		this.properties = properties;
	}
	
	public void addProperty(String key, String value) {
		PropertyCatalog property = new PropertyCatalog(this, key,value);
		properties.add(property);
	}
	
	public void addProperty(PropertyCatalog propertyCatalog) {
		properties.add(propertyCatalog);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CatalogItem other = (CatalogItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/* IMPORTANTE: implements PERSITABLE para pegar (catch) "duplicate key"
	 *             senão haverá update no método "save"
	 *  Spring JPA repository: prevent update on save
	 *  
	 *  - By default, a Property-ID inspection is performed, if it is null, then it is a new entity, otherwise is not.
        - If the entity implements Persistable the detection will 
          be delegated to the isNew() method implemented by the entity.
	 */
	
	@Override
	public boolean isNew() {
		return true;
	}


	
	
}