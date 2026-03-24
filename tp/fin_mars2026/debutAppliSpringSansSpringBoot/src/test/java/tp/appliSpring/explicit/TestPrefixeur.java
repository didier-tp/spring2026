package tp.appliSpring.explicit;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tp.appliSpring.exemple.ExempleConfig;
import tp.appliSpring.exemple.MonCalculateurCarre;
import tp.appliSpring.explicit.beans.Prefixeur;
import tp.appliSpring.explicit.conf.ExempleConfigExplicite;


//@RunWith(SpringRunner.class)  //si junit4
@ExtendWith(SpringExtension.class) //si junit5/jupiter
@ContextConfiguration(classes= {ExempleConfigExplicite.class})
@ActiveProfiles(profiles = {"maj"})
@Slf4j
public class TestPrefixeur {
	

	@Autowired
	private Prefixeur prefixeur; //à tester
	//private MonCalculateur monCalculateur; //à tester
	
	@Test
	public void testPrefixer() {
		String res = prefixeur.prefixer("java");
		log.debug("res="+res);
		Assertions.assertEquals("[[[[JAVA", res);
	}

}
