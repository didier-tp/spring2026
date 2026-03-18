package tp.appliSpring.bank.core.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import tp.appliSpring.AppliSpringApplication;
import tp.appliSpring.bank.core.model.Task;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes= {AppliSpringApplication.class})//reprendre la configuration de la classe principale
@ActiveProfiles({  "dev" })
//@ActiveProfiles({  "dev2" })
@Slf4j
public class TestServiceTask {

    @Autowired
    private ServiceTask serviceTask; //à tester

    @Test
    public void test1(){
        Task t1 = serviceTask.create(new Task(null,"devinette_1" , "quel animal a jamais soif ?"));//"zebu?"
        log.debug("t1="+t1);
        String idTaskAsString = t1.getNumero();
        assertNotNull(idTaskAsString);
        Task t1Relu = serviceTask.findById(idTaskAsString).get();
        log.debug("t1Relu="+t1Relu);
    }
}
