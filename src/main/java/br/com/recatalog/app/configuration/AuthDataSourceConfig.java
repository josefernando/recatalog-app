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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef =  "authEntityManagerFactory",
                       basePackages = {"br.com.recatalog.app.security.auth.repository"}, transactionManagerRef = "authTransactionManager")

public class AuthDataSourceConfig {

	@Bean(name = "authDataSource")
	@ConfigurationProperties(prefix = "spring.auth.datasource")
	public DataSource datasource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name="authEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder
				, @Qualifier("authDataSource") DataSource dataSource ) {
		
		Map<String,Object> properties = new HashMap<>();
		properties.put("hibernate.ddl-auto", "none");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
		
		return builder.dataSource(dataSource).properties(properties).packages("br.com.recatalog.app.security.auth.model").persistenceUnit("auth").build();
	}
	
	@Bean(name="authTransactionManager")
	public PlatformTransactionManager transactionManager(@Qualifier("authEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}	
}