package tp.appliSpring.bank.core.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import tp.appliSpring.AppliSpringApplication;
import tp.appliSpring.bank.core.exception.BankException;
import tp.appliSpring.bank.core.model.Client;
import tp.appliSpring.bank.core.model.Compte;
import tp.appliSpring.bank.core.model.Operation;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes= {AppliSpringApplication.class})//reprendre la configuration de la classe principale
@ActiveProfiles({  "dev" })
//@ActiveProfiles({  "dev2" })
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TestServiceCompte {

    private final ServiceCompte serviceCompte; //à tester

    private final ServiceOperation serviceOperation;

    private final ServiceClient serviceClient;

    @Test
    public void testVirement() {
        Compte compteASauvegarde = this.serviceCompte.create(
                new Compte(null, "compteA", 300.0));
        Compte compteBSauvegarde = this.serviceCompte.create(
                new Compte(null, "compteB", 100.0));
        long numCptA = compteASauvegarde.getNumero();
        long numCptB = compteBSauvegarde.getNumero();
        //remonter en memoire les anciens soldes des compte A et B avant virement
        //(+affichage console ou logger)
        double soldeA_avant = compteASauvegarde.getSolde();
        double soldeB_avant = compteBSauvegarde.getSolde();
        log.debug("avant bon virement, soldeA_avant=" + soldeA_avant +
                " et soldeB_avant=" + soldeB_avant);
        //effectuer un virement de 50 euros d'un compte A vers vers compte B
        this.serviceCompte.transfer(50.0, numCptA, numCptB);
        //remonter en memoire les nouveaux soldes des compte A et B apres virement
        // (+affichage console ou logger)
        Compte compteAReluApresVirement =
                this.serviceCompte.searchById(numCptA);
        Compte compteBReluApresVirement =
                this.serviceCompte.searchById(numCptB);
        double soldeA_apres = compteAReluApresVirement.getSolde();
        double soldeB_apres = compteBReluApresVirement.getSolde();
        log.debug("apres bon virement, soldeA_apres=" + soldeA_apres
                +
                " et soldeB_apres=" + soldeB_apres);
        //verifier -50 et +50 sur les différences de soldes sur A et B :
        Assertions.assertEquals(soldeA_avant - 50, soldeA_apres, 0.000001);
        Assertions.assertEquals(soldeB_avant + 50, soldeB_apres, 0.000001);
    }


    @Test
    public void testMauvaisVirement() {
        Compte compteASauvegarde = this.serviceCompte.create(
                new Compte(null, "compteA", 300.0));
        Compte compteBSauvegarde = this.serviceCompte.create(
                new Compte(null, "compteB", 100.0));
        long numCptA = compteASauvegarde.getNumero();
        long numCptB = compteBSauvegarde.getNumero();
        //remonter en memoire les anciens soldes des compte A et B avant virement
        //(+affichage console ou logger)
        double soldeA_avant = compteASauvegarde.getSolde();
        double soldeB_avant = compteBSauvegarde.getSolde();
        log.debug("avant mauvais virement, soldeA_avant=" + soldeA_avant +
                " et soldeB_avant=" + soldeB_avant);
        //effectuer un virement de 50 euros d'un compte A vers vers compte B
        try {
            this.serviceCompte.transfer(50.0, numCptA, -numCptB);
        } catch (BankException e) {
           // throw new RuntimeException(e);
            log.error("erreur normale attendue =, e=" + e.getMessage());
        }
        //remonter en memoire les nouveaux soldes des compte A et B apres virement
        // (+affichage console ou logger)
        Compte compteAReluApresVirement =
                this.serviceCompte.searchById(numCptA);
        Compte compteBReluApresVirement =
                this.serviceCompte.searchById(numCptB);
        double soldeA_apres = compteAReluApresVirement.getSolde();
        double soldeB_apres = compteBReluApresVirement.getSolde();
        log.debug("apres mauvais virement, soldeA_apres=" + soldeA_apres
                +  " et soldeB_apres=" + soldeB_apres);
        //verifier -0 et +0 sur les différences de soldes sur A et B :
        Assertions.assertEquals(soldeA_avant , soldeA_apres, 0.000001);
        Assertions.assertEquals(soldeB_avant , soldeB_apres, 0.000001);
    }

    @Test
    public void testCompteAvecOperation(){
        Compte cptA = serviceCompte.create(new Compte(null,"compteA",100.0));
        serviceOperation.create(new Operation(null,"achat 1" , -5.0 , new Date()), cptA.getNumero());
        serviceOperation.create(new Operation(null,"achat 2" , -6.0 , new Date()), cptA.getNumero());
        Compte cptB = serviceCompte.create(new Compte(null,"compteB",200.0));

        Compte compteAReluAvecOp = serviceCompte.searchById(cptA.getNumero());
        assertTrue(compteAReluAvecOp.getLabel().equals("compteA"));

        List<Operation> operations = serviceOperation.searchByCompte(cptA.getNumero());
        assertTrue(operations.size()==2);
        log.debug("operations de compteAReluAvecOp="+operations);
    }

    @Test
    public void testSearchCustomerAccounts(){
        Compte cptA = serviceCompte.create(new Compte(null,"compteZA",100.0));
        serviceOperation.create(new Operation(null,"achat 1" , -5.0 , new Date()),
                cptA.getNumero());
        serviceOperation.create(new Operation(null,"achat 2" , -6.0 , new Date()),
                cptA.getNumero());
        Compte cptB = serviceCompte.create(new Compte(null,"compteZB",200.0));

        Client client1 = serviceClient.create(new Client(null, "alex", "Therieur", "12 rue Elle 75001 Paris", "email1","pwd"));
        serviceCompte.fixerProprietaireCompte(cptA.getNumero(),client1.getNumero());
        serviceCompte.fixerProprietaireCompte(cptB.getNumero(),client1.getNumero());

        Compte cptC = serviceCompte.create(new Compte(null,"compteZC",20.0));
        Compte cptD = serviceCompte.create(new Compte(null,"compteZD",30.0));

        Client client2 = serviceClient.create(new Client(null, "axelle", "Aire", "13 rue Elle 75001 Paris", "email2","pwd"));
        serviceCompte.fixerProprietaireCompte(cptC.getNumero(),client2.getNumero());
        serviceCompte.fixerProprietaireCompte(cptD.getNumero(),client2.getNumero());

        List<Compte> comptesClient1 = serviceCompte.searchCustomerAccounts(client1.getNumero());
        //assertTrue(comptesClient1.size()==2);
        log.debug("comptesClient1="+comptesClient1);

    }


}
