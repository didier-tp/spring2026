package tp.appliSpring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class AppliSpringApplication {

	public static void main(String[] args) {
		//System.setProperty("spring.profiles.default","dev,reInit");
		SpringApplication.run(AppliSpringApplication.class, args);
		log.trace("http://localhost:8080/appliSpring");//en phase de dev
	}

}
