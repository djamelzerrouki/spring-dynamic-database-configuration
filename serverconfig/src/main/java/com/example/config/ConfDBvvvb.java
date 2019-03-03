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
@EnableJpaRepositories(entityManagerFactoryRef = "vvvbEntityManagerFactory", transactionManagerRef = "vvvbTransactionManager", basePackages = {
		"com.example.repository.vvvb" })
public class ConfDBvvvb {

	@Bean(name = "vvvbDataSource")
	@ConfigurationProperties(prefix = "spring.vvvb.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "vvvbEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean vvvbEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("vvvbDataSource") DataSource dataSource) {
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		return builder.dataSource(dataSource).properties(properties)
				.packages("com.example.model").persistenceUnit("vvvb").build();
	}

	@Bean(name = "vvvbTransactionManager")
	public PlatformTransactionManager vvvbTransactionManager(
			@Qualifier("vvvbEntityManagerFactory") EntityManagerFactory vvvbEntityManagerFactory) {
		return new JpaTransactionManager(vvvbEntityManagerFactory);
	}
}
