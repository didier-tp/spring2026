package tp.appliSpring.AppliSpringWeb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	//de l'exterieur java ... -Dspring.profiles.active=dev2,p2

	public static void main(String[] args) {
		//System.setProperty("spring.profiles.default","dev2");
		SpringApplication.run(Application.class, args);
		System.out.println("http://localhost:8080/springApp");
	}

}
