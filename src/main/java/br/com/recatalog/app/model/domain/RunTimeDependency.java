package br.com.recatalog.app.model.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TBRUNTIME_DEPENDENCY")
public class RunTimeDependency extends CatalogItem {
	private static final long serialVersionUID = 1L;
}