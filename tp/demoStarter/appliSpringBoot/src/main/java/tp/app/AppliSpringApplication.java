package tp.app;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import tp.conf.MyLibConfig;
import tp.lib.beans.Encadreur;
import tp.lib.beans.Suffixeur;
import tp.lib.beans.SuffixeurBasic;

/*
Rappel : @SpringBootApplication est une combinaison de @configuration , @EnableAutoconfiguration, @Componentscan(current_package)
et donc la classe principale est en soit une classe de configuration avec @Bean et @Import applicable
 */

@SpringBootApplication
//@Import({MyLibConfig.class}) //V1 sans starter automatique
public class AppliSpringApplication {

	public static void main(String[] args) {
		ApplicationContext springContext = SpringApplication.run(AppliSpringApplication.class, args);
		//url de l'appli
		System.out.println("tp.app.AppliSpringApplication started");
        try {
            Encadreur encadreur = springContext.getBean(Encadreur.class);
            String sEncadre=encadreur.encadrer("spring");
            System.out.println("sEncadre="+sEncadre);
        } catch (BeansException e) {
			System.err.println("aucun bean spring disponible de type Encadreur");
            //throw new RuntimeException(e);
        }
		System.exit(0);
    }

    /*
    @Bean
    public Suffixeur suffixeur(){
        return new SuffixeurBasic("___redefined_suffix___");
    }
    */


}
