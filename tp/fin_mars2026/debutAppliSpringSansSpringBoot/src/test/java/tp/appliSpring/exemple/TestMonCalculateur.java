package tp.appliSpring.exemple;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


//@RunWith(SpringRunner.class)  //si junit4
@ExtendWith(SpringExtension.class) //si junit5/jupiter
@ContextConfiguration(classes= {ExempleConfig.class})
@Slf4j
@ActiveProfiles(profiles = {"perf"})
public class TestMonCalculateur {
	
	//private static Logger log = LoggerFactory.getLogger(TestMonCalculateur.class);
	
	@Autowired @Qualifier("monCalculateurCarre")
	//private MonCalculateurCarre monCalculateur; //à tester
	private MonCalculateur monCalculateur; //à tester
	
	@Test
	public void testCalculer() {
		double res = monCalculateur.calculer(4);
		log.debug("pour x=4 , res="+res);
		Assertions.assertEquals(16.0, res,0.00000001);
	}

}
