package tp.appliSpring.bank.core.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@Slf4j
@SpringBootTest(classes= {tp.appliSpring.AppliSpringApplication.class})//reprendre la configuration de la classe principale
@ActiveProfiles({"dev"})
public class TestServiceGeo {

    @Autowired
    private ServiceGeo serviceGeo; //à tester

     @Test
    public void testGetVilleByCodePostal() {
         //boucle pour visualiser effet de @Cacheable
         for(int i=0; i<6; i++) {
             String ville75000 = serviceGeo.getVilleByCodePostal("75000");
             log.debug("ville pour code postal 75000 : " + ville75000);
             assert (ville75000.equals("Paris"));
             String ville69000 = serviceGeo.getVilleByCodePostal("69000");
             log.debug("ville pour code postal 69000 : " + ville69000);
             assert (ville69000.equals("Lyon"));
             try {
                 Thread.currentThread().sleep(600); //600ms
             } catch (InterruptedException e) {
                 throw new RuntimeException(e);
             }
         }
     }
}
