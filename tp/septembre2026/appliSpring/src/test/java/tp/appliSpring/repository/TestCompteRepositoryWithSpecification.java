package tp.appliSpring.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import tp.appliSpring.AppliSpringApplication;
import tp.appliSpring.criteria.SearchCriteria;
import tp.appliSpring.entity.CompteEntity;
import tp.appliSpring.specification.CompteEntitySpecification;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = {AppliSpringApplication.class})
@ActiveProfiles({ "dev"})  //pour analyser application-dev.properties
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired)) //injection par constructeur avec lombok et final
public class TestCompteRepositoryWithSpecification {


    private final CompteRepository compteRepository; //à tester

    @BeforeEach
    public void initAccountDataSet() {
        this.compteRepository.save(new CompteEntity(null,"compteAa",101.0));
        this.compteRepository.save(new CompteEntity(null,"compteBb",151.0));
        this.compteRepository.save(new CompteEntity(null,"compteCc",81.0));
        this.compteRepository.save(new CompteEntity(null,"compteDd",61.0));
    }


    @Test
    public void testComptesAvecSoldeMiniWithSpecification(){

        CompteEntitySpecification specSoldeMini =
                new CompteEntitySpecification(new SearchCriteria("solde", ">", "99"));

        List<CompteEntity> compteAvecSoldeSuperieurA99 = this.compteRepository.findAll(specSoldeMini);
        assertTrue(compteAvecSoldeSuperieurA99.size()>=2);
        log.debug("compteAvecSoldeSuperieurA99=" + compteAvecSoldeSuperieurA99);

    }
}
