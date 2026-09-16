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

    @Test
    public void testVirement() {
        CompteEntity compteASauvegarde = this.serviceCompte.saveOrUpdate( new CompteEntity(null,"compteA",300.0));
        CompteEntity compteBSauvegarde = this.serviceCompte.saveOrUpdate( new CompteEntity(null,"compteB",100.0));
        Long numCptA = compteASauvegarde.getNumero();
        Long numCptB = compteBSauvegarde.getNumero();
        //remonter en memoire les anciens soldes des compte A et B avant virement
        //(+affichage console ou logger) :
        double soldeA_avant= compteASauvegarde.getSolde();
        double soldeB_avant = compteBSauvegarde.getSolde();
        log.debug("avant bon virement, soldeA_avant="+soldeA_avant + " et soldeB_avant=" + soldeB_avant);
        //effectuer un virement de 50 euros d'un compte A vers vers compte B
        this.serviceCompte.transferer(50.0, numCptA, numCptB);
        //remonter en memoire les nouveaux soldes des compte A et B apres virement // (+affichage console ou logger)
        CompteEntity compteAReluApresVirement = this.serviceCompte.searchById(numCptA);
        CompteEntity compteBReluApresVirement = this.serviceCompte.searchById(numCptB);
        double soldeA_apres = compteAReluApresVirement.getSolde();
        double soldeB_apres = compteBReluApresVirement.getSolde();
        log.debug("apres bon virement, soldeA_apres="+soldeA_apres + " et soldeB_apres=" + soldeB_apres);
        //verifier -50 et +50 sur les différences de soldes sur A et B :
        Assertions.assertEquals(soldeA_avant - 50, soldeA_apres,0.000001);
        Assertions.assertEquals(soldeB_avant + 50, soldeB_apres,0.000001);
    }

    //Le test testMauvaisVirement() doit normalement réussir avec @Transactional sur transferer() dans ServiceCompteImpl
    //et echouer sinon
    @Test
    public void testMauvaisVirement() {
        CompteEntity compteASauvegarde = this.serviceCompte.saveOrUpdate( new CompteEntity(null,"compteA",300.0));
        CompteEntity compteBSauvegarde = this.serviceCompte.saveOrUpdate( new CompteEntity(null,"compteB",100.0));
        Long numCptA = compteASauvegarde.getNumero();
        Long numCptB = compteBSauvegarde.getNumero();
        //remonter en memoire les anciens soldes des compte A et B avant virement
        //(+affichage console ou logger) :
        double soldeA_avant= compteASauvegarde.getSolde();
        double soldeB_avant = compteBSauvegarde.getSolde();
        log.debug("avant mauvais virement, soldeA_avant="+soldeA_avant + " et soldeB_avant=" + soldeB_avant);
        //effectuer un virement de 50 euros d'un compte A vers vers compte -1 qui n'existe pas
        try {
            this.serviceCompte.transferer(50.0, numCptA, -1);
        } catch (Exception e) {
            System.err.println("erreur attendue:" + e.getMessage()); //exception normale/attendue car -1 n'existe pas
        }
        //remonter en memoire les nouveaux soldes des compte A et B apres virement // (+affichage console ou logger)
        CompteEntity compteAReluApresVirement = this.serviceCompte.searchById(numCptA);
        CompteEntity compteBReluApresVirement = this.serviceCompte.searchById(numCptB);
        double soldeA_apres = compteAReluApresVirement.getSolde();
        double soldeB_apres = compteBReluApresVirement.getSolde();
        log.debug("apres mauvais virement, soldeA_apres="+soldeA_apres + " et soldeB_apres=" + soldeB_apres);
        //verifier aucunes différences de soldes sur A et B (car si rollback bien fait rien doit changer):
        Assertions.assertEquals(soldeA_avant , soldeA_apres,0.000001);
        Assertions.assertEquals(soldeB_avant , soldeB_apres,0.000001);
    }

}
