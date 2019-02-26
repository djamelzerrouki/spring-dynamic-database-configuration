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
@EnableJpaRepositories(entityManagerFactoryRef = "cartenationalEntityManagerFactory", transactionManagerRef = "cartenationalTransactionManager", basePackages = {
		"com.example.repository.cartenational" })
public class ConfDBcartenational {

	@Bean(name = "cartenationalDataSource")
	@ConfigurationProperties(prefix = "spring.cartenational.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "cartenationalEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean cartenationalEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("cartenationalDataSource") DataSource dataSource) {
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		return builder.dataSource(dataSource).properties(properties)
				.packages("com.example.model","com.example.repository.cartenational").persistenceUnit("cartenational").build();
	}

	@Bean(name = "cartenationalTransactionManager")
	public PlatformTransactionManager cartenationalTransactionManager(
			@Qualifier("cartenationalEntityManagerFactory") EntityManagerFactory cartenationalEntityManagerFactory) {
		return new JpaTransactionManager(cartenationalEntityManagerFactory);
	}
}
