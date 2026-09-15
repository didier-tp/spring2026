package tp.appliSpring.exemple;

import org.springframework.stereotype.Component;
import tp.appliSpring.annotation.Aff;
import tp.appliSpring.annotation.LogExecutionTime;

@Component
//@Aff
public class MonCalculateurCarre implements MonCalculateur {

	@Override
	@LogExecutionTime
	public double calculer(double x) {
		return x*x;
	}

}
