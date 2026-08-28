package tp.conf;

import com.atomikos.spring.AtomikosDataSourceBean;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import tp.conf.properties.MyXaDataSourceProperties;

import javax.sql.DataSource;

@Configuration
@Profile("jta")
public class PurchaseDataSourceConfig {

    @Bean
    @Qualifier("purchaseDbRepositoryDb")
    @ConfigurationProperties("spring.purchasedb.datasource") //spring.purchasedb.datasource... in application-jta.properties
    public MyXaDataSourceProperties purchaseDbDataSourceProperties() {
        return new MyXaDataSourceProperties();
    }


    @Bean(name = "purchaseDbDataSource")
    public DataSource batchDataSource(
            @Qualifier("purchaseDbRepositoryDb") MyXaDataSourceProperties myXaDataSourceProperties
    ) {
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        BeanUtils.copyProperties(myXaDataSourceProperties,ds);
        return ds;
    }

}
