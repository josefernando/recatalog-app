package br.com.recatalog.app.model.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TBPROJECT")
public class Project extends CatalogItem {
	private static final long serialVersionUID = 1L;
}