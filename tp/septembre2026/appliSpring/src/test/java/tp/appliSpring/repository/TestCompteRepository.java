package tp.appliSpring.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import tp.appliSpring.entity.CompteEntity;

@SpringBootTest
@ActiveProfiles({ "dev"})  //pour analyser application-dev.properties
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired)) //injection par constructeur avec lombok et final
public class TestCompteRepository {


    private final CompteRepository compteRepository; //à tester

    @Test
    public void testAjoutCompteEtRelecture(){
        //hypothese : base avec tables vides au lancement du test
        CompteEntity compte = new CompteEntity(null,"compteA",100.0);
        CompteEntity compteSauvegarde = this.compteRepository.save(compte); //INSERT INTO
        log.debug("compteSauvegarde=" + compteSauvegarde);
        CompteEntity compteRelu = this.compteRepository.findById(compteSauvegarde.getNumero()).orElse(null); //SELECT
        Assertions.assertEquals("compteA",compteRelu.getLabel());
        Assertions.assertEquals(100.0,compteRelu.getSolde());
        log.debug("compteRelu apres insertion=" + compteRelu);
    }
}
