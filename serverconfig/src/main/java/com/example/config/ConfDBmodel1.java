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
@EnableJpaRepositories(entityManagerFactoryRef = "model1EntityManagerFactory", transactionManagerRef = "model1TransactionManager", basePackages = {
		"com.example.repository.model1" })
public class ConfDBmodel1 {

	@Bean(name = "model1DataSource")
	@ConfigurationProperties(prefix = "spring.model1.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "model1EntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean model1EntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("model1DataSource") DataSource dataSource) {
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		return builder.dataSource(dataSource).properties(properties)
				.packages("com.example.model","com.example.repository.model1").persistenceUnit("model1").build();
	}

	@Bean(name = "model1TransactionManager")
	public PlatformTransactionManager model1TransactionManager(
			@Qualifier("model1EntityManagerFactory") EntityManagerFactory model1EntityManagerFactory) {
		return new JpaTransactionManager(model1EntityManagerFactory);
	}
}
