package tp.appliSpring.exemplev2;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


public class MonCalculateurCarre implements MonCalculateur {

	@Override
	public double calculer(double x) {
		return x*x;
	}

}
