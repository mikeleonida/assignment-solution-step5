package com.stackroute.activitystream.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.transaction.TransactionManager;

//import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.hibernate4.HibernateTransactionManager;
//import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*This class will contain the application-context for the application. 
 * Define the following annotations:
 * @Configuration - Annotating a class with the @Configuration indicates that the 
 *                  class can be used by the Spring IoC container as a source of 
 *                  bean definitions
 * @EnableTransactionManagement - Enables Spring's annotation-driven transaction management capability.
 * @EnableJpaRepositories -  Will scan the package of the annotated configuration class for Spring Data repositories by default.                
 * */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories
@PropertySource(value = { "classpath:application.properties" })
public class PersistenceJPAConfig {
	
	@Autowired
	private Environment environment;
	
	/*
	 * Define the bean for EntityManagerFactory,
	 * LocalContainerEntityManagerFactoryBean gives full control over
	 * EntityManagerFactory configuration and is appropriate for environments where
	 * fine-grained customization is required. Create a new
	 * LocalContainerEntityManagerFactoryBean object. We need to create this object
	 * because it creates the JPA EntityManagerFactory.
	 */

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = 
				new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource());
		entityManagerFactory.setPackagesToScan(new String[] { "com.stackroute.activitystream.model" });
		entityManagerFactory.setJpaProperties(hibernateProperties());
		return entityManagerFactory;
	}
	
	/*
	 * Define the bean for DataSource. In our application, we are using MySQL as the
	 * dataSource. To create the DataSource bean, we need to know: 1. Driver class
	 * name 2. Database URL 3. Username 4. Password
	 */
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("spring.datasource.driverClassName"));
		dataSource.setUrl(environment.getRequiredProperty("spring.datasource.url"));
		dataSource.setUsername(environment.getRequiredProperty("spring.datasource.username"));
		dataSource.setPassword(environment.getRequiredProperty("spring.datasource.password"));
		return dataSource;
	}

	/*
	 * Define the bean for Transaction Manager, PlatformTransactionManager
	 * implements the programmatic approach to implement transactions.
	 */

	@Bean
	@Autowired
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return txManager;
	}
	
	/*
	 * Define the bean for PersistenceExceptionTranslationPostProcessor to enable
	 * exception translation.
	 */
	
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslationProcessor() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
	/*
	 * Define all the Hinernate properties
	 */

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		//properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
		
		properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		properties.put("jpa.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		
		//properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
		
		properties.put("spring.jpa.hibernate.ddl-auto", environment.getRequiredProperty("spring.jpa.hibernate.ddl-auto"));
		properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("spring.jpa.hibernate.ddl-auto"));
		return properties;
	}
	
}
