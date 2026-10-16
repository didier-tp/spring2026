package tp.appliSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AppliSpringApplication {
	
	
	public static void initProfiles() {
		//java .... -Dspring.profiles.active=reInit,dev
		String profilsActifs  = System.getProperty("spring.profiles.active");
		if(profilsActifs!=null) {
			System.out.println("spring.profiles.active="+profilsActifs);
		}else {
			String defaultProfils  = "dev,reInit";
			//String defaultProfils  = "dev,reInit,withSecurity";
			//String defaultProfils  = "dev,reInit,withSecurity,withoutOAuth2";
			//String defaultProfils  = "dev2,reInit";
			//String defaultProfils  = "prod";
			System.setProperty("spring.profiles.default", defaultProfils);
			System.out.println("spring.profiles.default="+defaultProfils);
		}
	}

	public static void main(String[] args) {
		initProfiles();
		SpringApplication.run(AppliSpringApplication.class, args);
		//url de l'appli
		System.out.println("http://localhost:8181/appliSpring");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		//or new BCryptPasswordEncoder(int strength) with strength between 4 and 31
	}


	//pour test temporaire de @ConditionalOnMissingBean()
	//sur xy.MySecurityConfig du sous projet mysecurity-autoconfigure :
	/*
	@Bean(name="permitAllListAsString")
	public String monBeanPrioritaire(){
		return "monChemin";
	}
    */


}
