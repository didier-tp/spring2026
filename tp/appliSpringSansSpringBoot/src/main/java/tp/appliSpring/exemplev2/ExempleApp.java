package tp.appliSpring.exemplev2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ExempleApp {
	public static void main(String[] args) {
		ApplicationContext contextSpring = new AnnotationConfigApplicationContext(
				ExempleConfigExpliciteAvecProperties.class
				//ExempleConfigExpliciteVersionSimple.class
				);
		//contextSpring représente un ensemble de composants pris en charge par spring
		//et qui est initialisé selon une ou plusieurs classes de configuration.
		
		MonCalculateur monCalculateur = contextSpring.getBean(MonCalculateur.class);
		//MonCalculateur monCalculateur = (MonCalculateur) contextSpring.getBean("monCalculateurCarre");
		System.out.println("res de .calculer=" + monCalculateur.calculer(4));// 4*4=16.0 ou autre
		
		MonAfficheur monAfficheur = contextSpring.getBean(MonAfficheur.class);
		monAfficheur.afficher("Spring");
		
			
		Coordinateur coordinateurPrisEnChargeParSpring =
				contextSpring.getBean(Coordinateur.class);
		coordinateurPrisEnChargeParSpring.calculerEtAfficher();
		
		/*
		CoordinateurAvecInjectionParConstructeur 
		  coordinateurPrisEnChargeParSpringAvecInjectionParConstructeur =
				contextSpring.getBean(CoordinateurAvecInjectionParConstructeur.class);
		coordinateurPrisEnChargeParSpringAvecInjectionParConstructeur.calculerEtAfficher();
		*/
		
		((AnnotationConfigApplicationContext) contextSpring).close();
	}
}
