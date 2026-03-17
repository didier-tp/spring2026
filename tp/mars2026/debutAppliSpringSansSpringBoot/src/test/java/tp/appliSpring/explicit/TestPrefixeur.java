package tp.appliSpring.explicit;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

import static org.junit.jupiter.api.Assertions.assertEquals;


//@RunWith(SpringRunner.class)  //si junit4
@ExtendWith(SpringExtension.class) //si junit5/jupiter
@ContextConfiguration(classes= {ExempleConfigExplicite.class})
@Slf4j
@ActiveProfiles({"maj"})
public class TestPrefixeur {
	
	//private static Logger log = LoggerFactory.getLogger(TestPrefixeur.class);
	
	@Autowired
	private final Prefixeur prefixeur=null; //à tester
	
	@Test
	public void testPrefixer() {
		String res = prefixeur.prefixer("spring");
		log.debug("pour message=spring , res="+res);
		assertEquals(">>>SPRING", res);
	}

}
