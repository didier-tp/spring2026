package tp.conf;

import org.springframework.beans.factory.annotation.Value;
import tp.appliSpring.explicit.beans.*;

@Configuration
public class ExempleConfigExplicite {


    @Value("${preferences.prefixe:#}")
    private String monPrefixe; //="#";

    @Value("${preferences.suffixe:#}")
    private String monSuffixe; //="#";


    public Prefixeur prefixeurMaj(){
        return new PrefixeurMaj(monPrefixe);
    }



    public Suffixeur suffixeurMaj(){
        return new SuffixeurMaj(monSuffixe);
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
