package tp.appliSpring.exemple;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ExempleApp {
	public static void main(String[] args) {
		
		
		ApplicationContext contextSpring = new AnnotationConfigApplicationContext(ExempleConfig.class);
		//contextSpring représente un ensemble de composants pris en charge par spring
		//et qui est initialisé selon une ou plusieurs classes de configuration.
		
		//MonCalculateur monCalculateur = contextSpring.getBean(MonCalculateur.class);
		//MonCalculateur monCalculateur = (MonCalculateur) contextSpring.getBean("monCalculateurCarre");
		MonCalculateur monCalculateur = contextSpring.getBean("monCalculateurCarre",MonCalculateur.class);
		System.out.println("resCalcul="+monCalculateur.calculer(4));//4*4=16.0 ou autre 
		
		//A completer ...
		Coordinateur coordinateurPrisEnChargeParSpring =
				contextSpring.getBean(Coordinateur.class);
		coordinateurPrisEnChargeParSpring.calculerEtAfficher();

		CoordinateurAvecInjectionParConstructeur coordinateur2PrisEnChargeParSpring =
				contextSpring.getBean(CoordinateurAvecInjectionParConstructeur.class);
		coordinateur2PrisEnChargeParSpring.calculerEtAfficher();
		
		((AnnotationConfigApplicationContext) contextSpring).close();
	}
}
