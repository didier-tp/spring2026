package tp.appliSpring.exemple;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("V2")
public class MonAfficheurV2 implements MonAfficheur {

	@Override
	public void afficher(String message) {
		System.out.println("**"+message);

	}

	@Override
	public void afficherMaj(String message) {
		System.out.println("**"+message.toUpperCase());
	}

}
