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
@EnableJpaRepositories(entityManagerFactoryRef = "myDataBaseEntityManagerFactory", transactionManagerRef = "myDataBaseTransactionManager", basePackages = {
		"com.example.repository.myDataBase" })
public class ConfDBmyDataBase {

	@Bean(name = "myDataBaseDataSource")
	@ConfigurationProperties(prefix = "spring.myDataBase.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "myDataBaseEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean myDataBaseEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("myDataBaseDataSource") DataSource dataSource) {
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		return builder.dataSource(dataSource).properties(properties)
				.packages("com.example.model","com.example.repository.myDataBase").persistenceUnit("myDataBase").build();
	}

	@Bean(name = "myDataBaseTransactionManager")
	public PlatformTransactionManager myDataBaseTransactionManager(
			@Qualifier("myDataBaseEntityManagerFactory") EntityManagerFactory myDataBaseEntityManagerFactory) {
		return new JpaTransactionManager(myDataBaseEntityManagerFactory);
	}
}
