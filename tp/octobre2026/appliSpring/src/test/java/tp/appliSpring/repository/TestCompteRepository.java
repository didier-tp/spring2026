package tp.appliSpring.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import tp.appliSpring.entity.CompteEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
