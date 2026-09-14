package tp.appliSpring.exemple;

import org.springframework.stereotype.Component;

@Component
public class MonCalculateurCarre implements MonCalculateur {

	@Override
	public double calculer(double x) {
		return x*x;
	}

}
