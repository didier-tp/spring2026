package tp.appliSpring.exemple;

import org.springframework.stereotype.Component;

@Component
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
