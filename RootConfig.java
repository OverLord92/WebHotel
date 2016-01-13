package com.vjezba.config;

import java.util.Properties;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages="com.*",
	excludeFilters={
			@Filter(type=FilterType.ANNOTATION, value=EnableWebMvc.class)
		})
public class RootConfig {
	
	@Bean
	public DataSource dataSource(){
		DataSource dataSource = null;
		
		JndiTemplate jndi = new JndiTemplate();
		
		try{
			dataSource = (DataSource)jndi.lookup("java:comp/env/jdbc/hotel");
		} catch(NamingException ex) {
			ex.printStackTrace();
		}
		
		return dataSource;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory(){
		LocalSessionFactoryBean sessionFactory = 
				new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[]{ "com.vjezba.beans" });
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.setProperty("javax.persistence.validation.group.pre-persist", 
				"com.postit.validationGroups.PersistenceValidationGroup");
		properties.setProperty("javax.persistence.validation.group.pre-update", 
				"com.postit.validationGroups.PersistenceValidationGroup");
		properties.setProperty("javax.persistence.validation.group.pre-remove", 
				"com.postit.validationGroups.PersistenceValidationGroup");
		properties.setProperty("hibernate.hbm2ddl.auto", "create");
		sessionFactory.setHibernateProperties(properties);
		
		return sessionFactory;
	}
	
	@Bean 
	public DataSourceTransactionManager transactionManager(){
		DataSourceTransactionManager dstm = new DataSourceTransactionManager();
	
		dstm.setDataSource(dataSource());
		return dstm;
	}
}
