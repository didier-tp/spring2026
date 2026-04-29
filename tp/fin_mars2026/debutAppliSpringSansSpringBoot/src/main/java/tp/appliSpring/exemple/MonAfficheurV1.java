package tp.appliSpring.exemple;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class MonAfficheurV1 implements MonAfficheur {

	@Override
	public void afficher(String message) {
		System.out.println(">>"+message);

	}


	@Override
	public void afficherMaj(String message) {
		System.out.println(">>"+message.toUpperCase());
	}

}
