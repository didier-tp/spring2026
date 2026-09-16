package tp.appliSpring.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import tp.appliSpring.AppliSpringApplication;
import tp.appliSpring.entity.CompteEntity;

@SpringBootTest(classes = {AppliSpringApplication.class})
@ActiveProfiles({ "dev"})  //pour analyser application-dev.properties
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired)) //injection par constructeur avec lombok et final
public class TestServiceCompte {

    private final ServiceCompte serviceCompte; //à injecter et tester

    @Test
    public void testFindCompteByNum(){
        CompteEntity compte = new CompteEntity(null,"compteA",100.0);
        CompteEntity compteSauvegarde = this.serviceCompte.saveOrUpdate(compte); //INSERT INTO
        log.debug("compteSauvegarde=" + compteSauvegarde);
        CompteEntity compteRelu = this.serviceCompte.findById(compteSauvegarde.getNumero()).orElse(null); //SELECT
        Assertions.assertEquals("compteA",compteRelu.getLabel());
        Assertions.assertEquals(100.0,compteRelu.getSolde());
        log.debug("compteRelu apres insertion=" + compteRelu);
    }

}
