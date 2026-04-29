package tp.appliRest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppliRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppliRestApplication.class, args);
		System.out.println("http://localhost:8080/appliRest");
	}

}
