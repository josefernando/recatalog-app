package br.com.recatalog.app.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class DataSourceConfiguration {
	private String dao;

	public String getDao() {
		return dao;
	}

	public void setDao(String dao) {
		this.dao = dao;
	}
}