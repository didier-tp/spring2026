package tp.appliSpring.exemple;

import org.springframework.stereotype.Component;
import tp.appliSpring.annotation.LogExecutionTime;

@Component
public class MonCalculateurDouble implements MonCalculateur {

	@Override
	@LogExecutionTime  //@Transactionnal
	public double calculer(double x) {
		return 2*x;
	}

}
