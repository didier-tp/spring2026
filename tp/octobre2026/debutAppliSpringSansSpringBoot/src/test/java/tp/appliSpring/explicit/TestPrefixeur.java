package tp.appliSpring.explicit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tp.appliSpring.exemple.ExempleConfig;
import tp.appliSpring.exemple.MonCalculateur;
import tp.appliSpring.explicit.beans.Prefixeur;
import tp.appliSpring.explicit.conf.ExempleConfigExplicite;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ActiveProfiles({"maj"})
@ExtendWith(SpringExtension.class) //si junit5/jupiter
@ContextConfiguration(classes= {ExempleConfigExplicite.class})
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired)) //avec combinaison de @Primary sur MonCalculateurCarre
@Slf4j
public class TestPrefixeur {
	

	private final Prefixeur prefixeur; //à tester
	
	@Test
	public void testPrefixer() {
		String res = prefixeur.prefixer("spring");
		log.debug("res="+res);
		assertEquals(">>>SPRING",res);
	}

}
