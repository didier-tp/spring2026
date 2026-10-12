package tp.appliSpring.core;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import tp.appliSpring.core.dao.DaoCompte;
import tp.appliSpring.core.entity.Compte;

//version sans springBoot
@Configuration
//@EnableAspectJAutoProxy
@ComponentScan(basePackages = { "tp.appliSpring.core" /*, "tp.appliSpring.aspect"*/})
public class MySpringApplication {

	public static void main(String[] args) {
		
		//System.setProperty("spring.profiles.active", "perf");
		System.setProperty("spring.profiles.active", "initDataSet");
		
		
		/*ApplicationContext*/ AnnotationConfigApplicationContext springContext = new
				AnnotationConfigApplicationContext(MySpringApplication.class) ;

		/*		
		//ServiceCompte serviceCompte = springContext.getBean(ServiceCompte.class);
		DaoCompte daoCompte = (DaoCompte) springContext.getBean("daoCompteJpa");
		
		Compte cptA = new Compte(null,"compteA",100.0);
		
		//Compte cptA_sauvegarde = serviceCompte.sauvegarderCompte(cptA);
		Compte cptA_sauvegarde = daoCompte.save(cptA);
		
		//Compte cptA_relu = serviceCompte.rechercherCompteParNumero(cptA_sauvegarde.getNumero());
		Compte cptA_relu = daoCompte.findById(cptA_sauvegarde.getNumero());
		System.out.println("cptA_relu="+cptA_relu);
		*/
		springContext.close();
	}

}
