package tp.appliSpring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class AppliSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppliSpringApplication.class, args);
		//FATAL,ERROR,WARN,INFO,DEBUG,TRACE
		log.info("http://localhost:8080/appliSpring"); // /index.html sous entendu (implicite)
	}

}
