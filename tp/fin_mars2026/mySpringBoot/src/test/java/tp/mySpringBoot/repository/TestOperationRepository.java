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
        /*Operation op1_cA = new Operation(null ,-5.5, "achat1");
        op1_cA.setCompte(cA);*/
        Operation op1_cA = new Operation(null ,-5.5, "achat1",new Date(),cA);
        repositoryOperation.save(op1_cA);

        List<Operation> operationsDeCa = repositoryOperation.findByCompteNumero(cA.getNumero());
        log.debug("operationsDeCa="+operationsDeCa);
        assertTrue(operationsDeCa.size()>=1);

    }
}
