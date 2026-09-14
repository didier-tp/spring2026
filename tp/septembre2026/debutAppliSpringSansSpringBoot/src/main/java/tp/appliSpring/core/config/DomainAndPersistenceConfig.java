package tp.appliSpring.core.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

//@Configuration
@EnableTransactionManagement() // "transactionManager" (not "txManager") is expected !!!
@ComponentScan(basePackages = { "tp.appliSpring.core.dao" ,  "tp.appliSpring.core.service" ,  "tp.appliSpring.core.init"})
public class DomainAndPersistenceConfig {
	
	@Value("${typebase}")
	private String typeBase = "H2";
	
	@Value("${spring.jpa.action}")
	private String actionJpa = "none"; // ou bien "drop-and-create"

	// JpaVendorAdapter (Hibernate ou OpenJPA ou ...)
	//@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setShowSql(false);
		hibernateJpaVendorAdapter.setGenerateDdl(false);
		switch(typeBase) {
			case "MYSQL" : hibernateJpaVendorAdapter.setDatabase(Database.MYSQL); break;
			case "H2" : hibernateJpaVendorAdapter.setDatabase(Database.H2); break;
			case "POSTGRESQL" : hibernateJpaVendorAdapter.setDatabase(Database.POSTGRESQL); break;
		}
		return hibernateJpaVendorAdapter;
	}

	// EntityManagerFactory
	//@Bean(name = { "entityManagerFactory" })
	public EntityManagerFactory entityManagerFactory(JpaVendorAdapter jpaVendorAdapter, DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(jpaVendorAdapter);
		factory.setPackagesToScan("tp.appliSpring.core.entity");
		factory.setDataSource(dataSource);
		Properties jpaProperties = new Properties(); // java.util
		jpaProperties.setProperty("javax.persistence.schema-generation.database.action", actionJpa); //JPA>=2.1
		factory.setJpaProperties(jpaProperties);
		factory.afterPropertiesSet();
		return factory.getObject();
	} 
	
	
	// Transaction Manager for JPA or ...
	//@Bean(name = "transactionManager") 
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory);
		return txManager;
	}
  
}
