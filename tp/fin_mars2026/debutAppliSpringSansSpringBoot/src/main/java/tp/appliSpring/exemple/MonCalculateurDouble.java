package tp.appliSpring.exemple;

import org.springframework.stereotype.Component;

@Component
public class MonCalculateurDouble implements MonCalculateur {

	@Override
	public double calculer(double x) {
		return 2*x;
	}

}
