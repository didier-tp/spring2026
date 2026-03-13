package tp.appliSpring.exemple;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Primary
@Component()
public class MonCalculateurDouble implements MonCalculateur {

	@Override
	public double calculer(double x) {
		return 2*x;
	}

}
