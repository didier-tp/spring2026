package tp.appliSpring.core;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import tp.appliSpring.core.entity.Compte;
import tp.appliSpring.core.service.ServiceCompte;


public class MySpringApplication {

	public static void main(String[] args) {
		
		//System.setProperty("spring.profiles.active", "perf");
		
		/*ApplicationContext*/ ClassPathXmlApplicationContext springContext = new
				ClassPathXmlApplicationContext("/applicationContext.xml") ;

				
		ServiceCompte serviceCompte = springContext.getBean(ServiceCompte.class);
		Compte cptA = new Compte(null,"compteA",100.0);
		Compte cptA_sauvegarde = serviceCompte.sauvegarderCompte(cptA);
		
		Compte cptA_relu = serviceCompte.rechercherCompteParNumero(cptA_sauvegarde.getNumero());
		System.out.println("cptA_relu="+cptA_relu);
		
		springContext.close();
	}

}
