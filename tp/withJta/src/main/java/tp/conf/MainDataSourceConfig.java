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
public class MainDataSourceConfig {

    @Bean
    @Qualifier("mainDbRepositoryDb")
    @ConfigurationProperties("spring.datasource")
    public MyXaDataSourceProperties mainDbDataSourceProperties() {
        return new MyXaDataSourceProperties();
    }

    //NB: "batchDataSource" name is less important than @Primary
    @Bean(name = "mainDbDataSource")
    @Primary
    public DataSource batchDataSource(
            @Qualifier("mainDbRepositoryDb") MyXaDataSourceProperties myXaDataSourceProperties
    ) {
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        BeanUtils.copyProperties(myXaDataSourceProperties,ds);
        return ds;
    }

}
