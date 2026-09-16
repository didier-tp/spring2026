package tp.appliSpring.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import tp.appliSpring.AppliSpringApplication;
import tp.appliSpring.entity.CompteEntity;
import tp.appliSpring.entity.OperationEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = {AppliSpringApplication.class})
@ActiveProfiles({ "dev"})  //pour analyser application-dev.properties
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired)) //injection par constructeur avec lombok et final
public class TestOperationRepository {

    private final OperationRepository operationRepository; //à tester
    private final CompteRepository compteRepository; //pour aider à tester

    @Test
    public void testAjoutCompteEtRelecture(){
        //hypothese : base avec tables vides au lancement du test
        CompteEntity compteA = this.compteRepository.save(new CompteEntity(null,"compteAx",100.0));
        CompteEntity compteB = this.compteRepository.save(new CompteEntity(null,"compteBy",150.0));

        operationRepository.save(new OperationEntity(null,"opA1",-5.5, LocalDate.now(),compteA));
        operationRepository.save(new OperationEntity(null,"opA2",-6.5, LocalDate.now(),compteA));
        operationRepository.save(new OperationEntity(null,"opB1",-7.5, LocalDate.now(),compteB));
        operationRepository.save(new OperationEntity(null,"opB2",-8.5, LocalDate.now(),compteB));

        List<OperationEntity> operationsDuCompteA = operationRepository.findByCompteNumero(compteA.getNumero());
        assertTrue(operationsDuCompteA.size()==2);
        log.debug("operationsDuCompteA="+operationsDuCompteA);
    }


}
