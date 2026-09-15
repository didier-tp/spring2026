package tp.appliSpring.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import tp.appliSpring.entity.CompteEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

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

        //tester findBySoldeGreaterThanEqual() ou findBySoldeMini():
        this.compteRepository.save(new CompteEntity(null,"compteB",150.0));
        this.compteRepository.save(new CompteEntity(null,"compteC",80.0));
        this.compteRepository.save(new CompteEntity(null,"compteD",60.0));

        List<CompteEntity> compteAvecSoldeAuMoins100 = this.compteRepository.findBySoldeGreaterThanEqual(100.0);
        //List<CompteEntity> compteAvecSoldeAuMoins100 = this.compteRepository.findBySoldeMini(100.0); //ok
        assertTrue(compteAvecSoldeAuMoins100.size()>=2);
        log.debug("compteAvecSoldeAuMoins100=" + compteAvecSoldeAuMoins100);

    }

    //@Test
    //@Sql({"/import_comptes.sql"})
    public void testComptesAvecSoldeMini(){

        List<CompteEntity> compteAvecSoldeAuMoins2000 = this.compteRepository.findBySoldeGreaterThanEqual(2000.0);
        //List<CompteEntity> compteAvecSoldeAuMoins2000 = this.compteRepository.findBySoldeMini(2000.0); //ok
        assertTrue(compteAvecSoldeAuMoins2000.size()>=2);
        log.debug("compteAvecSoldeAuMoins2000=" + compteAvecSoldeAuMoins2000);

    }
}
