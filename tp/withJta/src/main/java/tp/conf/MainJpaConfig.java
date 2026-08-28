package tp.conf;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Profile("jta")
@EnableJpaRepositories(
        entityManagerFactoryRef = "mainEntityManager",
        transactionManagerRef = "atomikosJtaTransactionManager",
        //transactionManagerRef = "mainJpaTransactionManager",
        basePackages = {"tp.dao.main"}
)
public class MainJpaConfig {

    @Bean(name = "mainEntityManager")
    public LocalContainerEntityManagerFactoryBean mainEntityManager(EntityManagerFactoryBuilder builder,
                                                                          @Qualifier("mainDbDataSource") DataSource dataSource){


        return builder
                .dataSource(dataSource)
                .packages("tp.entity.main")
                .persistenceUnit("main")
                .properties(additionalJpaProperties())
                .build();

    }

    Map<String,?> additionalJpaProperties(){
        Map<String,String> map = new HashMap<>();

        //very important properties for JTA mode (multiple databases )
        map.put("hibernate.transaction.jta.platform", "org.hibernate.engine.transaction.jta.platform.internal.AtomikosJtaPlatform");
        map.put("javax.persistence.transactionType", "JTA");

        //secondary properties
        map.put("hibernate.hbm2ddl.auto", "none");
        //map.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        map.put("hibernate.show_sql", "true");

        return map;
    }

    /*
    //Non JTA , transaction on only one database
    @Bean(name = "mainJpaTransactionManager")
    public JpaTransactionManager transactionManager(@Qualifier("mainEntityManager") EntityManagerFactory emf){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }
    */
}

/*

transactionManager is defined in conf.AtomikosConfig
tp.dao.main is base packages for Spring Data JPA Repository interfaces :
    ProductRepository , CustomerRepository , OrderRepository

 */