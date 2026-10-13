package org.example.explicit.conf;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.example.explicit.beans.*;

@Configuration
@PropertySources({
        @PropertySource("classpath:application.properties")
        //, @PropertySource("classpath:prop2.properties")
})


public class ExempleConfigExplicite {

    //A faire en Tp:
    //phase 0 : faire fonctionner l'exemple tel quel

    //phase 1 : tenir compte des choix de prefixe et suffixe de exemples.properties
    //via @Value("${...:..}")

    //avec dans src/main/resources/exemples.properties
    //preferences.prefixe=>>>
    //preferences.suffixe=<<<
    //tester le comportement en modifiant les valeurs des prefixe et suuffixe dans le .properties

    @Value("${preferences.prefixe:___}") // avec ___ comme valeur par défaut si pas de preferences.prefixe dans .proiperties
    private String monPrefixe; //="#";

    @Value("${preferences.suffixe:___}") // avec ___ comme valeur par défaut si pas de preferences.suffixe dans .proiperties
    private String monSuffixe="#";

    //phase 2 : variante ...Basic en l'absence de profile "maj"
    //et vraiante ...Maj si présence du profile "maj"
    //tester le comportement en activant ou pas de profile "maj" en début de main()

    //idee :  public Prefixeur prefixeur() avec profile "!maj" et public Prefixeur prefixeurMaj() avec profile "maj"

    @Bean
    @Profile("!maj")
    @ConditionalOnMissingBean
    public Prefixeur prefixeur(){
        return new PrefixeurBasic(monPrefixe);
    }


    @Bean
    @Profile("!maj")
    @ConditionalOnMissingBean
    public Suffixeur suffixeur(){
        return new SuffixeurBasic(monSuffixe);
    }

    @Bean
    @Profile("maj")
    @ConditionalOnMissingBean
    public Prefixeur prefixeurMaj(){
        return new PrefixeurMaj(monPrefixe);
    }


    @Bean
    @Profile("maj")
    @ConditionalOnMissingBean
    public Suffixeur suffixeurMaj(){
        return new SuffixeurMaj(monSuffixe);
    }

    @Bean(name="encadreurSpring")
    @ConditionalOnMissingBean
    public Encadreur encadreurSpring( Prefixeur prefixeurSpring , Suffixeur suffixeurSpring){
        //EncadreurImpl encadreurImpl =  new EncadreurImpl(prefixeurSpring,suffixeurSpring);
        EncadreurImpl encadreurImpl =  new EncadreurImpl();
        encadreurImpl.setPrefixeur(prefixeurSpring);
        encadreurImpl.setSuffixeur(suffixeurSpring);
        return encadreurImpl;
    }
}
