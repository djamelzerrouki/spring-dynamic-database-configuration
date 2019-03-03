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
@EnableJpaRepositories(entityManagerFactoryRef = "ddddEntityManagerFactory", transactionManagerRef = "ddddTransactionManager", basePackages = {
		"com.example.repository.dddd" })
public class ConfDBdddd {

	@Bean(name = "ddddDataSource")
	@ConfigurationProperties(prefix = "spring.dddd.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "ddddEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean ddddEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("ddddDataSource") DataSource dataSource) {
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		return builder.dataSource(dataSource).properties(properties)
				.packages("com.example.model").persistenceUnit("dddd").build();
	}

	@Bean(name = "ddddTransactionManager")
	public PlatformTransactionManager ddddTransactionManager(
			@Qualifier("ddddEntityManagerFactory") EntityManagerFactory ddddEntityManagerFactory) {
		return new JpaTransactionManager(ddddEntityManagerFactory);
	}
}
