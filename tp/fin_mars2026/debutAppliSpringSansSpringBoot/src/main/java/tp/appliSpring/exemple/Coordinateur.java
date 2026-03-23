package tp.appliSpring.exemple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Coordinateur {
	

	@Autowired
	private MonAfficheur monAfficheur=null; //référence vers afficheur à injecter

	@Autowired
	private MonCalculateur monCalculateur=null;//référence vers calculateur à injecter
	
	

	public void calculerEtAfficher() {
		double x=4;
		double res =monCalculateur.calculer(x); //x*x ou bien 2*x ou bien ...
		monAfficheur.afficher("res="+res);// >> res=16 en v1 ou bien ** res=16
	}
}
