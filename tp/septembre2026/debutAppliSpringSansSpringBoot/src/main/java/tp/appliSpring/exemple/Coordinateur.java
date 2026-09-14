package tp.appliSpring.exemple;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Coordinateur {
	
	@Autowired @Qualifier("monAfficheurV2") //ou bien @Qualifier("monAfficheurV1")
	private MonAfficheur monAfficheur=null; //référence vers afficheur à injecter
	
	@Autowired @Qualifier("monCalculateurCarre") //ou bien @Qualifier("monCalculateurDouble")
	private MonCalculateur monCalculateur=null;//référence vers calculateur à injecter

	public Coordinateur(){
		System.out.println("dans constructeur de Coordinateur, monAfficheur="+this.monAfficheur);
		System.out.println("dans constructeur de Coordinateur, monCalculateur="+this.monCalculateur);
	}

	@PostConstruct
	public void init(){
		System.out.println("dans init() de Coordinateur, monAfficheur="+this.monAfficheur);
		System.out.println("dans init() avec @PostContruct, monCalculateur="+this.monCalculateur);
	}
	

	public void calculerEtAfficher() {
		double x=4;
		double res =monCalculateur.calculer(x); //x*x ou bien 2*x ou bien ...
		monAfficheur.afficher("res="+res);// >> res=16 en v1 ou bien ** res=16
	}
}
