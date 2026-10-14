package tp.appliSpring.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import tp.appliSpring.model.Compte;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest()
@ActiveProfiles({  "dev" , "reinit" })
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class TestServiceCompte {

    private final ServiceCompte serviceCompte; //à tester

    @Test
    public void testSearchById(){
        Compte compte = serviceCompte.searchById(1L);
        assertNotNull(compte);
        log.debug("compte="+compte);
    }

}
