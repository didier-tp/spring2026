package tp.mySpringBoot.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tp.mySpringBoot.entity.Compte;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Slf4j
public class TestCompteRepository {

    @Autowired
    private RepositoryCompte daoCompte; //à tester

    @Test
    public void testFindByLabel() {
        this.daoCompte.save(new Compte(null,"compteC1",100.0));
        this.daoCompte.save(new Compte(null,"compteC2",150.0));
        this.daoCompte.save(new Compte(null,"compte3",150.0));
        List<Compte> comptes =daoCompte.findByLabelLike("compteC%");
        log.debug("comptes=" + comptes);
        assertTrue(comptes.size()>=1);

    }

    @Test
    public void testFindSoldeBetween() {
        this.daoCompte.save(new Compte(null,"compteCc1",100.0));
        this.daoCompte.save(new Compte(null,"compteCc2",120.0));
        this.daoCompte.save(new Compte(null,"compteCc3",150.0));
        this.daoCompte.save(new Compte(null,"compteCc4",180.0));
        //List<Compte> comptes =daoCompte.findBySoldeBetween(110,170);
        List<Compte> comptes =daoCompte.rechercherAvecSoldeEntre(110,170); //à coder via @Query()
        log.debug("comptes avec solde entre 110 et 170=" + comptes);
        assertTrue(comptes.size()>=2);

    }

    @Test
    public void testAjoutEtRelectureEtSuppression() {
        //hypothese : base avec tables vides au lancement du test
        Compte compte = new Compte(null,"compteA",100.0);
        Compte compteSauvegarde = this.daoCompte.save(compte); //INSERT INTO
        log.debug("compteSauvegarde=" + compteSauvegarde);

        Compte compteRelu = this.daoCompte.findById(compteSauvegarde.getNumero()).get(); //SELECT
        Assertions.assertEquals("compteA",compteRelu.getLabel());
        Assertions.assertEquals(100.0,compteRelu.getSolde());
        log.debug("compteRelu apres insertion=" + compteRelu);

        compte.setSolde(150.0); compte.setLabel("compte_a");
        Compte compteMisAjour = this.daoCompte.save(compte); //UPDATE
        log.debug("compteMisAjour=" + compteMisAjour);

        compteRelu = this.daoCompte.findById(compteSauvegarde.getNumero()).get(); //SELECT
        Assertions.assertEquals("compte_a",compteRelu.getLabel());
        Assertions.assertEquals(150.0,compteRelu.getSolde());
        log.debug("compteRelu apres miseAjour=" + compteRelu);
		/*
		//+supprimer :
		this.daoCompte.deleteById(compteSauvegarde.getNumero());

		//verifier bien supprimé (en tentant une relecture qui renvoi null)
		Compte compteReluApresSuppression = this.daoCompte.findById(compteSauvegarde.getNumero());
		Assertions.assertTrue(compteReluApresSuppression == null);
		*/
    }
}
