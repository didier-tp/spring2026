package tp.appliSpring.bank.persistence.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import tp.appliSpring.AppliSpringApplication;

import java.text.SimpleDateFormat;


@SpringBootTest(classes= {AppliSpringApplication.class})//reprendre la configuration de la classe principale
@ActiveProfiles({  "dev" })
@Slf4j
public class TestOperationDaoSql {


    @Autowired
    private CompteRepository daoCompte; //pour aider à tester

    @Autowired
    private OperationRepository daoOperation;  //à tester

    //@Test
    //A COMPLETER EN TP via @Sql et /import_comptes_with_operations.sql
    //de src/test/resources
    public void testFindForCompteWithDateBetween() throws Exception{

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        /*
        List<OperationEntity> operations = daoOperation.findForCompteWithDateBetween(1002L,
                dateFormat.parse("2025-02-01") ,
                dateFormat.parse("2025-03-15"));
        assertTrue(operations.size()==2);
        log.debug("operations entre 2025-02-01 et 2025-03-15 ="+operations);
        */
    }


}
