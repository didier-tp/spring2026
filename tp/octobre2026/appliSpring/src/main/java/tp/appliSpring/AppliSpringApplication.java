package tp.appliSpring;

import lombok.extern.slf4j.Slf4j;
/*
import org.example.explicit.beans.Encadreur;
import org.example.explicit.beans.EncadreurImpl;
import org.example.explicit.beans.PrefixeurBasic;
import org.example.explicit.beans.SuffixeurBasic;
*/
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Slf4j
//@Import(org.example.explicit.conf.ExempleConfigExplicite.class)
public class AppliSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppliSpringApplication.class, args);
		//FATAL,ERROR,WARN,INFO,DEBUG,TRACE
		log.info("http://localhost:8080/appliSpring"); // /index.html sous entendu (implicite)
	}


	/*
	//test temporaire de starter personnalisé avec @ConditionalOnMissingBean
	@Bean(name="encadreurSpring")
	public Encadreur EncadreurRedefini(){
		EncadreurImpl encadreurImpl =  new EncadreurImpl();
		encadreurImpl.setPrefixeur(new PrefixeurBasic("#"));
		encadreurImpl.setSuffixeur(new SuffixeurBasic("#"));
		return encadreurImpl;
	}
    */


}
