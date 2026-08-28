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
        entityManagerFactoryRef = "purchaseEntityManager",
        transactionManagerRef = "atomikosJtaTransactionManager",
        basePackages = {"tp.dao.purchase"}
)
public class PurchaseJpaConfig {

    @Bean(name = "purchaseEntityManager")
    public LocalContainerEntityManagerFactoryBean purchaseEntityManager(EntityManagerFactoryBuilder builder,
                                                                          @Qualifier("purchaseDbDataSource") DataSource dataSource){


        return builder
                .dataSource(dataSource)
                .packages("tp.entity.purchase")
                .persistenceUnit("purchase")
                .properties(additionalJpaProperties())
                .build();

    }

    Map<String,?> additionalJpaProperties(){
        Map<String,String> map = new HashMap<>();

        //very important properties for JTA mode (multiple databases )
        map.put("hibernate.transaction.jta.platform", "org.hibernate.engine.transaction.jta.platform.internal.AtomikosJtaPlatform");
        map.put("javax.persistence.transactionType", "JTA");

        map.put("hibernate.hbm2ddl.auto", "none");
        //map.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        //map.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
       // map.put("hibernate.show_sql", "true");

        return map;
    }
}

/*

transactionManager is defined in conf.AtomikosConfig
tp.dao.purchase is base packages for Spring Data JPA Repository interfaces :
    PurchaseRepository

 */