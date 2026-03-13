package tp.appliSpring.exemple;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
@RequiredArgsConstructor()
public class CoordinateurAvecInjectionParConstructeur {


	private final MonAfficheur monAfficheur; //référence vers afficheur à injecter
	
	private final MonCalculateur monCalculateur;//référence vers calculateur à injecter

	CoordinateurAvecInjectionParConstructeur(){
		this.monAfficheur=null;
		this.monCalculateur=null;
	}


	public void calculerEtAfficher() {
		double x=4;
		double res =monCalculateur.calculer(x); //x*x ou bien 2*x ou bien ...
		monAfficheur.afficher("my res="+res);// >> res=16 en v1 ou bien ** res=16
	}
}


/*

@Component
public class CoordinateurAvecInjectionParConstructeur {

	private MonAfficheur monAfficheur; //référence vers afficheur à injecter

	private MonCalculateur monCalculateur;//référence vers calculateur à injecter


	@Autowired
	public CoordinateurAvecInjectionParConstructeur(
			@Qualifier("V1") MonAfficheur monAfficheur,
			@Qualifier("monCalculateurCarre")MonCalculateur monCalculateur) {
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
 */