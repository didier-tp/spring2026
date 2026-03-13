package tp.appliSpring.exemplev2;

import jakarta.annotation.PostConstruct;


public class CoordinateurAvecInjectionParConstructeur {
	
	private MonAfficheur monAfficheur; //référence vers afficheur à injecter
	
	private MonCalculateur monCalculateur;//référence vers calculateur à injecter
	
	

	public CoordinateurAvecInjectionParConstructeur(
			 MonAfficheur monAfficheur, 
			 MonCalculateur monCalculateur) {
		super();
		this.monAfficheur = monAfficheur;
		this.monCalculateur = monCalculateur;
	}

	public CoordinateurAvecInjectionParConstructeur() {
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
