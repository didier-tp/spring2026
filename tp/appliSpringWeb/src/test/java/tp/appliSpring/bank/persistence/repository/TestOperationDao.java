package tp.appliSpring.bank.persistence.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import tp.appliSpring.AppliSpringApplication;
import tp.appliSpring.bank.persistence.entity.CompteEntity;
import tp.appliSpring.bank.persistence.entity.OperationEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(classes= {AppliSpringApplication.class})//reprendre la configuration de la classe principale
@ActiveProfiles({  "dev" })
@Slf4j
public class TestOperationDao {

    @Autowired
    private CompteRepository daoCompte; //pour aider à tester

    @Autowired
    private OperationRepository daoOperation;  //à tester

    @Test
    public void testFindForCompteWithDateBetween() throws Exception{

        CompteEntity compteA = this.daoCompte.save(new CompteEntity(null,"compteA",200.0));
        OperationEntity op1CptA = new OperationEntity(null,"achat 1a" , -15.0 , new Date());
        op1CptA.setCompte(compteA);this.daoOperation.save(op1CptA);
        OperationEntity op2CptA = new OperationEntity(null,"achat 2a" , -16.0 , new Date());
        op2CptA.setCompte(compteA);this.daoOperation.save(op2CptA);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        CompteEntity compteB = this.daoCompte.save(new CompteEntity(null,"compteB",200.0));
        OperationEntity op1CptB = new OperationEntity(null,"achat 1b" , -12.0 , dateFormat.parse("2025-01-23"));
        op1CptB.setCompte(compteB);this.daoOperation.save(op1CptB);
        OperationEntity op2CptB = new OperationEntity(null,"achat 2b" , -13.0 , dateFormat.parse("2025-02-12"));
        op2CptB.setCompte(compteB);this.daoOperation.save(op2CptB);
        OperationEntity op3CptB = new OperationEntity(null,"achat 3b" , -17.0 , dateFormat.parse("2025-03-11"));
        op3CptB.setCompte(compteB);this.daoOperation.save(op3CptB);
        OperationEntity op4CptB = new OperationEntity(null,"achat 4b" , -9.0 , dateFormat.parse("2025-03-18"));
        op4CptB.setCompte(compteB);this.daoOperation.save(op4CptB);

        List<OperationEntity> operations = daoOperation.findForCompteWithDateBetween(compteB.getNumero(),
                dateFormat.parse("2025-02-01") ,
                dateFormat.parse("2025-03-15"));
        assertTrue(operations.size()==2);
        log.debug("operations entre 2025-02-01 et 2025-03-15 ="+operations);

    }


}
