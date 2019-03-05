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
@EnableJpaRepositories(entityManagerFactoryRef = "pasporteEntityManagerFactory", transactionManagerRef = "pasporteTransactionManager", basePackages = {
		"com.example.repository.pasporte" })
public class ConfDBpasporte {

	@Bean(name = "pasporteDataSource")
	@ConfigurationProperties(prefix = "spring.pasporte.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "pasporteEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean pasporteEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("pasporteDataSource") DataSource dataSource) {
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		return builder.dataSource(dataSource).properties(properties)
				.packages("com.example.model","com.example.repository.pasporte").persistenceUnit("pasporte").build();
	}

	@Bean(name = "pasporteTransactionManager")
	public PlatformTransactionManager pasporteTransactionManager(
			@Qualifier("pasporteEntityManagerFactory") EntityManagerFactory pasporteEntityManagerFactory) {
		return new JpaTransactionManager(pasporteEntityManagerFactory);
	}
}
