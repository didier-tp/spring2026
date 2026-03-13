package tp.appliSpring.exemplev2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
//NB: exceptionnellement ici pas de @ComponentScan pour
//ne pas charger les autres versions (AvecProperties, ...)
public class ExempleConfigExpliciteVersionSimple  {
	
	@Bean //id/nom par defaut = nom de la methode = "calculateurCarre"
	//@Bean("monCalculateurCarre")
	public MonCalculateur calculateur() {
		//return new MonCalculateurCarre();
		return new MonCalculateurDouble();
	}

    @Bean
	public MonAfficheur afficheur() {
		//return new MonAfficheurV1();
		return new MonAfficheurV2();
	}
	
	@Bean
	//NB: les paramètres d'entrés seront créés en premiers par Spring
	//pour pouvoir être ensuite injectés ici pour construire le coordinateur
	public Coordinateur coordinateur(MonAfficheur afficheur, MonCalculateur calculateur) {
			Coordinateur coordinateur  = new Coordinateur();
			coordinateur.setMonAfficheur(afficheur); //avec .setter généré par eclipse
			coordinateur.setMonCalculateur(calculateur); //avec .setter généré par eclipse
			return coordinateur; 
	}
	
}
