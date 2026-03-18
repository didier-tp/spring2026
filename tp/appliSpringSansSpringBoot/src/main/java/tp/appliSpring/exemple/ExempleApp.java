package tp.appliSpring.exemple;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ExempleApp {
	public static void main(String[] args) {
		System.setProperty("spring.profiles.active", "reInit,perf");
		//System.setProperty("spring.profiles.active", "reInit");
		
		ApplicationContext contextSpring = new AnnotationConfigApplicationContext(ExempleConfig.class);
		//contextSpring représente un ensemble de composants pris en charge par spring
		//et qui est initialisé selon une ou plusieurs classes de configuration.
		
		MonCalculateur monCalculateur = contextSpring.getBean(MonCalculateur.class);
		//MonCalculateur monCalculateur = (MonCalculateur) contextSpring.getBean("monCalculateurCarre");
		System.out.println("res de .calculer=" + monCalculateur.calculer(4));// 4*4=16.0 ou autre
		
		Coordinateur coordinateurPrisEnChargeParSpring =
				contextSpring.getBean(Coordinateur.class);
		coordinateurPrisEnChargeParSpring.calculerEtAfficher();
		
		CoordinateurAvecInjectionParConstructeur 
		  coordinateurPrisEnChargeParSpringAvecInjectionParConstructeur =
				contextSpring.getBean(CoordinateurAvecInjectionParConstructeur.class);
		coordinateurPrisEnChargeParSpringAvecInjectionParConstructeur.calculerEtAfficher();
		
		
		((AnnotationConfigApplicationContext) contextSpring).close();
	}
}
