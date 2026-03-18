package tp.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tp.conf.properties.EncadreurProperties;
import tp.lib.beans.*;


@Configuration
@ConfigurationPropertiesScan("tp.conf.properties")
public class MyLibConfig {


    @Value("${encadreur.prefixe:#}")
    private String monPrefixe; //="#";

    @Value("${encadreur.suffixe:#}")
    private String monSuffixe; //="#";

    @Autowired(required = false)
    public EncadreurProperties encadreurProperties;

    @Bean
    @ConditionalOnMissingBean(name="prefixeur")
    public Prefixeur prefixeur(){
        return new PrefixeurBasic(monPrefixe);
    }

    @Bean
    @ConditionalOnMissingBean(name="suffixeur")
    public Suffixeur suffixeur(){
        return new SuffixeurBasic(monSuffixe);
    }

    @Bean
    @ConditionalOnMissingBean(name="encadreur")
    public Encadreur encadreur(Prefixeur prefixeurSpring , Suffixeur suffixeurSpring){
        //EncadreurImpl encadreurImpl =  new EncadreurImpl(prefixeurSpring,suffixeurSpring);
        EncadreurImpl encadreurImpl =  new EncadreurImpl();
        encadreurImpl.setPrefixeur(prefixeurSpring);
        encadreurImpl.setSuffixeur(suffixeurSpring);
        return encadreurImpl;
    }
}
