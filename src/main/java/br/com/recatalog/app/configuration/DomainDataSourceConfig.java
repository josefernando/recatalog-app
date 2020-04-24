package br.com.recatalog.app.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef =  "domainEntityManagerFactory",
                       basePackages = {"br.com.recatalog.app.domain.repository"}, transactionManagerRef = "domainTransactionManager")

public class DomainDataSourceConfig {

	@Primary
	@Bean(name = "domainDataSource")
	@ConfigurationProperties(prefix = "spring.domain.datasource")
	public DataSource datasource() {
		return DataSourceBuilder.create().build();
	}
	
	@Primary
	@Bean(name="domainEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder
				, @Qualifier("domainDataSource") DataSource dataSource ) {
		
		Map<String,Object> properties = new HashMap<>();
		properties.put("hibernate.ddl-auto", "none");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
		
		return builder.dataSource(dataSource).properties(properties).packages("br.com.recatalog.app.model.domain").persistenceUnit("Domain").build();
	}
	
	@Primary
	@Bean(name="domainTransactionManager")
	public PlatformTransactionManager transactionManager(@Qualifier("domainEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}	
}