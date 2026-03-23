package tp.appliSpring.exemple;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Coordinateur {
	

	@Autowired @Qualifier("monAfficheurV1")
	private MonAfficheur monAfficheur=null; //référence vers afficheur à injecter

	@Autowired @Qualifier("monCalculateurCarre")
	private MonCalculateur monCalculateur=null;//référence vers calculateur à injecter
	
	public Coordinateur(){
		System.out.println("dans constructeur Coordinateur() , monCalculateur=" + monCalculateur);
	}

	@PostConstruct
	public void initialiser(){
		System.out.println("dans initialiser() préfixé par @PostConstruct , monCalculateur=" + monCalculateur);
	}

	public void calculerEtAfficher() {
		double x=4;
		double res =monCalculateur.calculer(x); //x*x ou bien 2*x ou bien ...
		monAfficheur.afficher("res="+res);// >> res=16 en v1 ou bien ** res=16
	}
}
