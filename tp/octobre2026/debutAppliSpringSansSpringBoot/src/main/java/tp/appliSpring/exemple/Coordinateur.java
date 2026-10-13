package tp.appliSpring.exemple;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Coordinateur {

	//essai temporaire qui ne fonctionne pas si prog par aspect mise en oeuvre:
	//@Autowired
	//private MonCalculateurCarre monCalculateurCarre;
	//raison du problème : @EnableAspectJAutoProxy fait en sorte que le code généré au démarrage de l'apppli
	//par les mécanismes "Aspects" (classe ayant un nom comme jdk.proxy2.$Proxy27) remplace l'instance initiale
	//de la classe MonCalculateurCarre (avec même nom de bean "monCalculateurCarre" --> ça génère un petit décalage
	//sur le type exact (exception BeanNotOfRequiredTypeException)
	//bonne pratique : injection au maximum avec type abstrait (interface) pour ne pas être embêté .
	
	@Autowired @Qualifier("monAfficheurV1")
	private MonAfficheur monAfficheur=null; //référence vers afficheur à injecter
	
	@Autowired @Qualifier("monCalculateurDouble")
	//@Resource(name = "monCalculateurDouble")
	private MonCalculateur monCalculateur=null;//référence vers calculateur à injecter

	public Coordinateur() {
		System.out.println("dans constructeur Coordinateur() , monAfficheur="+monAfficheur);
	}

	@PostConstruct
	public void initialiser(){
		System.out.println("dans initialiser() prefixé par @PostConstruct, monAfficheur="+monAfficheur);
	}

	public void calculerEtAfficher() {
		double x=4;
		double res =monCalculateur.calculer(x); //x*x ou bien 2*x ou bien ...
		monAfficheur.afficher("res="+res);// >> res=16 en v1 ou bien ** res=16
	}
}
