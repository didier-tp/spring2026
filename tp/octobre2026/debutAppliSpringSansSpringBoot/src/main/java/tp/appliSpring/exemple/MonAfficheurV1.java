package tp.appliSpring.exemple;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import tp.appliSpring.annotation.LogExecutionTime;

@Component
@Primary
public class MonAfficheurV1 implements MonAfficheur {

	@Override
	@LogExecutionTime
	public void afficher(String message) {
		System.out.println(">>"+message);

	}

	@Override
	public void afficherMaj(String message) {
		System.out.println(">>"+message.toUpperCase());
	}

}
