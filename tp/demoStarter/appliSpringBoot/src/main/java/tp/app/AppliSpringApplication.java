package tp.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AppliSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppliSpringApplication.class, args);
		//url de l'appli
		System.out.println("tp.app.AppliSpringApplication started");
	}

}
