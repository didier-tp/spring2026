package tp.appliSpring.AppliSpringWeb.dao;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tp.appliSpring.AppliSpringWeb.entity.Compte;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Slf4j
public class TestDaoCompte {

    @Autowired
    private DaoCompte daoCompte; //chose prise en charge par spring à tester

    @Test
    public void testAjoutRelecture(){
        Compte c1 = new Compte(null,"compte_1",100.0);
        this.daoCompte.save(c1); //déclenche INSERT INTO et auto_increment
        log.info("c1 sauvegardé:" + c1.toString());
        Compte c1Relu = this.daoCompte.findById(c1.getNumero()).get();
        log.info("c1Relu="+c1Relu);
        assertEquals("compte_1",c1Relu.getLabel());
        assertEquals(100.0 , c1Relu.getSolde(), 0.000001);

    }
}
