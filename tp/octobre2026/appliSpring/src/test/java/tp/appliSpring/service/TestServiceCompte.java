package tp.appliSpring.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import tp.appliSpring.entity.CompteEntity;
import tp.appliSpring.model.Compte;
import tp.appliSpring.repository.CompteRepository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest()
@ActiveProfiles({  "dev" , "reinit" })
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class TestServiceCompte {

    private final ServiceCompte serviceCompte; //à tester
    private final CompteRepository compteRepository; //pour aider à tester

    @Test
    public void testSearchById(){
        Compte compte = serviceCompte.searchById(1L);
        assertNotNull(compte);
        log.debug("compte="+compte);
    }

    @Test
    public void testVirement() {
        CompteEntity compteA = this.compteRepository.save(new CompteEntity(null,"compteAx",100.0));
        CompteEntity compteB = this.compteRepository.save(new CompteEntity(null,"compteBy",150.0));
        double soldeA_avant = compteA.getSolde();
        double soldeB_avant = compteB.getSolde();
        log.debug("avant bon virement, soldeA_avant=" + soldeA_avant + " soldeB_avant="+soldeB_avant);
        this.serviceCompte.transferer(50,compteA.getNumero(),compteB.getNumero());
        CompteEntity compteAReluApres = this.compteRepository.findById(compteA.getNumero()).get();
        CompteEntity compteBReluApres = this.compteRepository.findById(compteB.getNumero()).get();
        double soldeA_apres = compteAReluApres.getSolde();
        double soldeB_apres = compteBReluApres.getSolde();
        log.debug("apres bon virement, soldeA_apres=" + soldeA_apres + " soldeB_apres="+soldeB_apres);
        Assertions.assertEquals(soldeA_avant - 50, soldeA_apres,0.000001);
        Assertions.assertEquals(soldeB_avant + 50, soldeB_apres,0.000001);
    }

    @Test
    public void testMauvaisVirement() {
        CompteEntity compteA = this.compteRepository.save(new CompteEntity(null,"compteAx",100.0));
        CompteEntity compteB = this.compteRepository.save(new CompteEntity(null,"compteBy",150.0));
        double soldeA_avant = compteA.getSolde();
        double soldeB_avant = compteB.getSolde();
        log.debug("avant mauvais virement, soldeA_avant=" + soldeA_avant + " soldeB_avant="+soldeB_avant);
        try {
            //erreur volontaire pour expérimenter rollback et @Transactional:
            this.serviceCompte.transferer(50,compteA.getNumero(),- compteB.getNumero());
        } catch (Exception e) {
            System.out.println("exception normalement attendue:" + e.getMessage());
        }
        CompteEntity compteAReluApres = this.compteRepository.findById(compteA.getNumero()).get();
        CompteEntity compteBReluApres = this.compteRepository.findById(compteB.getNumero()).get();
        double soldeA_apres = compteAReluApres.getSolde();
        double soldeB_apres = compteBReluApres.getSolde();
        log.debug("apres mauvais virement, soldeA_apres=" + soldeA_apres + " soldeB_apres="+soldeB_apres);
        Assertions.assertEquals(soldeA_avant , soldeA_apres,0.000001); //RIEN doit changer après rollback/echec
        Assertions.assertEquals(soldeB_avant , soldeB_apres,0.000001); //RIEN doit changer après rollback/echec
    }

}
