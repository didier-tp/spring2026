package tp.mySpringBoot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class MySpringBootApplication {

	public static void main(String[] args) {

		SpringApplication.run(MySpringBootApplication.class, args);
		//System.out.println("http://localhost:8080/mySpringBoot");
		log.info("http://localhost:8080/mySpringBoot");
		//ou bien log.debug() ou ...

	}

}
