package br.com.recatalog.app.security.auth.model;

import javax.persistence.Table;

@Table(name = "tbrole")
public class Role extends Permission {
	private static final long serialVersionUID = 1L;
}