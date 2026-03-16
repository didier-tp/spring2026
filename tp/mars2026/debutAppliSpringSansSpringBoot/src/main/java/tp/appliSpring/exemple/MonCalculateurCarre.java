package tp.appliSpring.exemple;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class MonCalculateurCarre implements MonCalculateur {

	@Override
	public double calculer(double x) {
		return x*x;
	}

}
