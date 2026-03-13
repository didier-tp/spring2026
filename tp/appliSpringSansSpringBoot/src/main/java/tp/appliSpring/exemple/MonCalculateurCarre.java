package tp.appliSpring.exemple;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import tp.appliSpring.annotation.LogExecutionTime;

@Primary  //version principale (à utiliser si plusieurs versions possibles et si pas de @Qualifier)
@Component()//id par defaut = monCalculateurCarre = NomClasse avec minuscule au debut
public class MonCalculateurCarre implements MonCalculateur {

	@LogExecutionTime
	public double calculer(double x) {
		return x*x;
	}

}
