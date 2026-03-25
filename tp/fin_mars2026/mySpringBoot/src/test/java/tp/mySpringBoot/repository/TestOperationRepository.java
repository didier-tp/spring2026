package tp.mySpringBoot.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tp.mySpringBoot.entity.Compte;
import tp.mySpringBoot.entity.Operation;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Slf4j
public class TestOperationRepository {

    @Autowired
    private  RepositoryOperation repositoryOperation; //à tester

    @Autowired
    private RepositoryCompte repositoryCompte; //pour aider à tester

    @Test
    public void testFindOperationByCompteNumero(){
        Compte cA = repositoryCompte.save(new Compte(null,"cA" , 100.0));
        /*Operation op1_cA = new Operation(null ,-5.5, "achat1");  op1_cA.setCompte(cA);*/
        Operation op1_cA = new Operation(null ,-5.5, "achat1a",new Date(),cA);
        Operation op2_cA = new Operation(null ,-15.5, "achat2a",new Date(),cA);
        repositoryOperation.save(op1_cA); repositoryOperation.save(op2_cA);

        Compte cB = repositoryCompte.save(new Compte(null,"cB" , 100.0));
        Operation op1_cB = new Operation(null ,-6.5, "achat1b",new Date(),cB);
        Operation op2_cB = new Operation(null ,-18.5, "achat2b",new Date(),cB);
        repositoryOperation.save(op1_cB); repositoryOperation.save(op2_cB);

        List<Operation> operationsDeCa = repositoryOperation.findByCompteNumero(cA.getNumero());
        log.debug("operationsDeCa="+operationsDeCa);
        assertTrue(operationsDeCa.size()>=2);

        //Compte cArelu = repositoryCompte.findById(cA.getNumero()).get();
        Compte cArelu = repositoryCompte.findCompteWithOperationsById(cA.getNumero()).get();
        log.debug("cArelu="+cArelu.toString());
        for(Operation op : cArelu.getOperations()){
            log.debug("\top:" +op);
        }
    }
}
