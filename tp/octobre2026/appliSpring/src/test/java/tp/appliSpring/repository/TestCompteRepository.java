package tp.appliSpring.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import tp.appliSpring.entity.CompteEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest()
@ActiveProfiles({  "dev" })
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class TestCompteRepository {

    private final CompteRepository compteRepository; //à tester

    @Test
    public void testAjoutCompteEtrelecture(){
        CompteEntity compte = new CompteEntity(null,"compteA",100.0);
        CompteEntity compteSauvegarde = this.compteRepository.save(compte); //INSERT INTO , saveOrUpdate
        log.debug("compteSauvegarde=" + compteSauvegarde);
        CompteEntity compteRelu = this.compteRepository.findById(compteSauvegarde.getNumero()).orElse(null); //SELECT
        assertEquals("compteA",compteRelu.getLabel());
        assertEquals(100.0,compteRelu.getSolde());
        log.debug("compteRelu apres insertion=" + compteRelu);
    }

    @Test
    public void testRechercherCompteAvecSoldeMini(){
        this.compteRepository.save(new CompteEntity(null,"compteC1",100.0));
        this.compteRepository.save(new CompteEntity(null,"compteC2",20.0));
        this.compteRepository.save(new CompteEntity(null,"compteC3",150.0));
        this.compteRepository.save(new CompteEntity(null,"compteC4",60.0));
        //List<CompteEntity> comptesAvecSoldeMini80 = compteRepository.rechercherCompteAvecSoldeMini(80.0);
        List<CompteEntity> comptesAvecSoldeMini80 = compteRepository.findBySoldeGreaterThanEqual(80.0);
        assertTrue(comptesAvecSoldeMini80.size()>=2);
        log.debug("comptesAvecSoldeMini80=" + comptesAvecSoldeMini80);
    }
}
