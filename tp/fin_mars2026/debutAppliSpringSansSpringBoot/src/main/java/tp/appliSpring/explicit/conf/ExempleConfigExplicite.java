package tp.appliSpring.explicit.conf;

import org.springframework.context.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import tp.appliSpring.explicit.beans.*;

@Configuration
@PropertySource("classpath:exemples.properties")
//classpath: tous les endroits où sont recherchées choses java et fichiers de config
//entre autres: src/main/resources
public class ExempleConfigExplicite {

    //phase 2 : variante ...Basic en l'absence de profile "maj"
    //et vraiante ...Maj si présence du profile "maj"
    //tester le comportement en activant ou pas de profile "maj" en début de main()

    @Value("${preferences.prefixe:#}")
    private  String monPrefixe; //="#";

    @Value("${preferences.suffixe:#}")
    private  String monSuffixe; //="#";

    @Bean
    public Prefixeur prefixeur(){
        return new PrefixeurBasic(monPrefixe);
    }


    @Bean
    public Suffixeur suffixeur(){
        return new SuffixeurBasic(monSuffixe);
    }

    @Bean
    public Encadreur encadreurSpring( Prefixeur prefixeurSpring , Suffixeur suffixeurSpring){
        //EncadreurImpl encadreurImpl =  new EncadreurImpl(prefixeurSpring,suffixeurSpring);
        EncadreurImpl encadreurImpl =  new EncadreurImpl();
        encadreurImpl.setPrefixeur(prefixeurSpring);
        encadreurImpl.setSuffixeur(suffixeurSpring);
        return encadreurImpl;
    }
}
