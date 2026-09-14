package tp.appliSpring.explicit.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tp.appliSpring.explicit.beans.Encadreur;
import tp.appliSpring.explicit.beans.Prefixeur;
import tp.appliSpring.explicit.conf.ExempleConfigExplicite;

public class SpringAppWithExplicitConf {
	public static void main(String[] args) {

		//Choisir éventuellement des profiles à activer AVANT l'initialisation du context spring:
		//en fixant la valeur de la propriété spring.profiles.active ou bien spring.profiles.default

		ApplicationContext contextSpring = new AnnotationConfigApplicationContext(ExempleConfigExplicite.class);
		//contextSpring représente un ensemble de composants pris en charge par spring
		//et qui est initialisé selon une ou plusieurs classes de configuration.
		
		Prefixeur monPrefixeur = contextSpring.getBean(Prefixeur.class);
		System.out.println("message avec prefixe="+monPrefixeur.prefixer("spring"));//**spring ou autre
		
		//A completer ...
		Encadreur monEncadreur = contextSpring.getBean(Encadreur.class);
		System.out.println("message avec encadrement="+monEncadreur.encadrer("java"));//**java** ou autre

		((AnnotationConfigApplicationContext) contextSpring).close();
	}
}
