package br.com.recatalog.app.model.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TBPROPERTY_CATALOG_ITEM")
public class PropertyCatalog implements Serializable {
	private static final long serialVersionUID = 1L;
		
	public PropertyCatalog() {}
	
	public PropertyCatalog(CatalogItem catalog, String key, String value) {
		this.propertyId = new PropertyId_(catalog, key,  value);
	}
	
	// Em caso de erro 
	// To resolve this problem you must disable JPA validation for builds 
	// in validtion properties

	@EmbeddedId 
	private PropertyId_ propertyId;

	public PropertyId_ getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(PropertyId_ propertyId) {
		this.propertyId = propertyId;
	}
	
	public CatalogItem getParent() {
		return getPropertyId().getCatalog();
	}
	
	public String getKey() {
		return getPropertyId().getKey();
	}
	
	public String getValue() {
		return getPropertyId().getValue();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((propertyId == null) ? 0 : propertyId.hashCode());
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
		PropertyCatalog other = (PropertyCatalog) obj;
		if (propertyId == null) {
			if (other.propertyId != null)
				return false;
		} else if (!propertyId.equals(other.propertyId))
			return false;
		return true;
	}

	
	
}

@Embeddable
class PropertyId_ implements Serializable  {  
	private static final long serialVersionUID = 1L;
	
	public PropertyId_() {}
	
	public PropertyId_(CatalogItem catalog, String key, String value) {
		this.catalog = catalog;
		this.key     = key;
		this.value   = value;
	}
	
	@ManyToOne
	@JoinColumn(name="FK_CATALOG_ID", referencedColumnName="ID")
	CatalogItem catalog;
	
	@Column(name="PROPERTY_KEY")
    String key;
	
	@Column(name="PROPERTY_VALUE")
    String value;	    
    
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public CatalogItem getCatalog() {
		return catalog;
	}

	public void setCatalog(CatalogItem catalog) {
		this.catalog = catalog;
	}
}