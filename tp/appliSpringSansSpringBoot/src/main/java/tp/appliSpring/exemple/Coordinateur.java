package tp.appliSpring.exemple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;

@Component
public class Coordinateur {
	
	@Autowired 
	//@Qualifier("monAfficheurV1") //si pas de @Qualifier sur la classe MonAfficheurV1
	@Qualifier("V1") //si @Qualifier sur la classe MonAfficheurV1
	private MonAfficheur monAfficheur=null; //référence vers afficheur à injecter
	
	@Autowired
	//@Inject
	@Qualifier("monCalculateurCarre") //si pas de @Qualifier sur la classe MonCalculateurCarre
	private MonCalculateur monCalculateur=null;//référence vers calculateur à injecter
	
	
	
	
	public Coordinateur() {
		super();
		System.out.println("dans constructeur , monAfficheur=" + monAfficheur + "...");
	}
	
	@PostConstruct
	public void initialisation() {
		System.out.println("dans initialisation, monAfficheur=" + monAfficheur + "...");
	}




	public void calculerEtAfficher() {
		double x=4;
		double res =monCalculateur.calculer(x); //x*x ou bien 2*x ou bien ...
		monAfficheur.afficher("res="+res);// >> res=16 en v1 ou bien ** res=16
	}
}
