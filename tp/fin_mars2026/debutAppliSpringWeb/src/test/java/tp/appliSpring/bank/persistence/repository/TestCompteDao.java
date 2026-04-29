package tp.appliSpring.bank.persistence.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import tp.appliSpring.AppliSpringApplication;
import tp.appliSpring.bank.persistence.entity.CompteEntity;
import tp.appliSpring.bank.persistence.entity.OperationEntity;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(classes= {AppliSpringApplication.class})//reprendre la configuration de la classe principale
@ActiveProfiles({  "dev" })
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired) //pour injection de dépendances via constructeur (final fields)
public class TestCompteDao {

    private final CompteRepository daoCompte; //à tester

    private final OperationRepository daoOperation; //pour aider à tester

    /*
    @Autowired
    private  CompteRepository daoCompte; //à tester

    @Autowired
    private  OperationRepository daoOperation; //pour aider à tester
    */

    @Test
    public void testWithOperations(){

        CompteEntity compteA = this.daoCompte.save(new CompteEntity(null,"compteA",200.0));
        OperationEntity op1CptA = new OperationEntity(null,"achat 1a" , -15.0 , new Date());
        op1CptA.setCompte(compteA);this.daoOperation.save(op1CptA);
        OperationEntity op2CptA = new OperationEntity(null,"achat 2a" , -16.0 , new Date());
        op2CptA.setCompte(compteA);this.daoOperation.save(op2CptA);

        CompteEntity compteB = this.daoCompte.save(new CompteEntity(null,"compteB",200.0));
        OperationEntity op1CptB = new OperationEntity(null,"achat 1b" , -12.0 , new Date());
        op1CptB.setCompte(compteB);this.daoOperation.save(op1CptB);
        OperationEntity op2CptB = new OperationEntity(null,"achat 2b" , -13.0 , new Date());
        op2CptB.setCompte(compteB);this.daoOperation.save(op2CptB);

        //Compte compteBReluAvecOp = daoCompte.findById(compteB.getNumero()).orElse(null);
        CompteEntity compteBReluAvecOp = daoCompte.findWithOperations(compteB.getNumero());
        assertTrue(compteBReluAvecOp.getLabel().equals("compteB"));
        assertTrue(compteBReluAvecOp.getOperations().size()==2);
        log.debug("compteBReluAvecOp="+compteBReluAvecOp);
        log.debug("operations de compteBReluAvecOp="+compteBReluAvecOp.getOperations());

    }

    @Test
    //@DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void testWFindBySoldeGreaterThanEqual(){
        CompteEntity compteA1 = this.daoCompte.save(new CompteEntity(null,"compteA1",100.0));
        CompteEntity compteA2 = this.daoCompte.save(new CompteEntity(null,"compteA2",200.0));
        CompteEntity compteA3 = this.daoCompte.save(new CompteEntity(null,"compteA3",300.0));
        CompteEntity compteA4 = this.daoCompte.save(new CompteEntity(null,"compteA4",400.0));
        List<CompteEntity> liste = daoCompte.findBySoldeGreaterThanEqual(150.0);
        log.debug("liste="+liste);
        //assertTrue(liste.size()==3);
        assertTrue(liste.size()>=3);
    }


    @Test
    public void testAjoutEtRelectureEtSuppression() {
        //hypothese : base avec tables vides au lancement du test
        CompteEntity compte = new CompteEntity(null,"compteA",100.0);
        CompteEntity compteSauvegarde = this.daoCompte.save(compte); //INSERT INTO
        log.debug("compteSauvegarde=" + compteSauvegarde);

        CompteEntity compteRelu = this.daoCompte.findById(compteSauvegarde.getNumero()).orElse(null); //SELECT
        Assertions.assertEquals("compteA",compteRelu.getLabel());
        Assertions.assertEquals(100.0,compteRelu.getSolde());
        log.debug("compteRelu apres insertion=" + compteRelu);

        compte.setSolde(150.0); compte.setLabel("compte_a");
        CompteEntity compteMisAjour = this.daoCompte.save(compte); //UPDATE
        log.debug("compteMisAjour=" + compteMisAjour);

        compteRelu = this.daoCompte.findById(compteSauvegarde.getNumero()).orElse(null); //SELECT
        Assertions.assertEquals("compte_a",compteRelu.getLabel());
        Assertions.assertEquals(150.0,compteRelu.getSolde());
        log.debug("compteRelu apres miseAjour=" + compteRelu);

		/*
		//+supprimer :
		this.daoCompte.deleteById(compteSauvegarde.getNumero());

		//verifier bien supprimé (en tentant une relecture qui renvoi null)
		CompteEntity compteReluApresSuppression = this.daoCompte.findById(compteSauvegarde.getNumero()).orElse(null);
		Assertions.assertTrue(compteReluApresSuppression == null);
		*/
    }

}
