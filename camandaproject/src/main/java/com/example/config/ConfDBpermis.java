package com.example.config;

import java.util.HashMap;

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
@EnableJpaRepositories(entityManagerFactoryRef = "permisEntityManagerFactory", transactionManagerRef = "permisTransactionManager", basePackages = {
		"com.example.repository.permis" })
public class ConfDBpermis {

	@Bean(name = "permisDataSource")
	@ConfigurationProperties(prefix = "spring.permis.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "permisEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean permisEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("permisDataSource") DataSource dataSource) {
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		return builder.dataSource(dataSource).properties(properties)
				.packages("com.example.model","com.example.repository.permis").persistenceUnit("permis").build();
	}

	@Bean(name = "permisTransactionManager")
	public PlatformTransactionManager permisTransactionManager(
			@Qualifier("permisEntityManagerFactory") EntityManagerFactory permisEntityManagerFactory) {
		return new JpaTransactionManager(permisEntityManagerFactory);
	}
}
