package tp.appliSpring.exemple;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CoordinateurAvecInjectionParConstructeur {


	private final MonAfficheur monAfficheur=null; //référence vers afficheur à injecter
	
	private final MonCalculateur monCalculateur=null;//référence vers calculateur à injecter

	/*
	//Autre solution pour lever ambiguité sur choses à injecter:
	//placer @Primary sur une des versions (la propriété)
	public CoordinateurAvecInjectionParConstructeur(
			@Qualifier("monAfficheurV1") MonAfficheur monAfficheur,
			@Qualifier("monCalculateurCarre") MonCalculateur monCalculateur) {
		super();
		this.monAfficheur = monAfficheur;
		this.monCalculateur = monCalculateur;
	}
	*/


	public void calculerEtAfficher() {
		double x=4;
		double res =monCalculateur.calculer(x); //x*x ou bien 2*x ou bien ...
		monAfficheur.afficher("res="+res);// >> res=16 en v1 ou bien ** res=16
	}
}
