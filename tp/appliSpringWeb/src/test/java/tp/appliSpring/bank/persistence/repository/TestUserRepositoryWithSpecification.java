package tp.appliSpring.bank.persistence.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;
import tp.appliSpring.AppliSpringApplication;
import tp.appliSpring.bank.persistence.criteria.SearchCriteria;
import tp.appliSpring.bank.persistence.entity.UserEntity;
import tp.appliSpring.bank.persistence.specif.UserEntitySpecification;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes= {AppliSpringApplication.class})//reprendre la configuration de la classe principale
@ActiveProfiles({  "dev" })
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired) //pour injection de dépendances via constructeur (final fields)
public class TestUserRepositoryWithSpecification {

    private final UserRepository repository;
    private UserEntity userAlex;
    private UserEntity userAlain;

    @BeforeEach
    public void initUserDataSet() {
        userAlex = new UserEntity(null,"Alex","Therieur","alex.therieur@xyz.com",22);
        repository.save(userAlex);

        userAlain = new UserEntity(null,"Alain","Therieur","alain.therieur@xyz.com",26);
        repository.save(userAlain);
    }

    @Test
    public void testFindByLastNameWithSpecification(){
        UserEntitySpecification spec =
                new UserEntitySpecification(new SearchCriteria("lastName", ":", "Therieur"));

        List<UserEntity> results = repository.findAll(spec);
        assertTrue(results.size()>=2);
        assertThat(results).usingRecursiveFieldByFieldElementComparator().contains(userAlain,userAlex);
        log.debug("users with specif 'firstName : Therieur' : " + results);

    }

    @Test
    public void testFindByLastNameAndMinimumAgeWithSpecification(){
        UserEntitySpecification spec1 =
                new UserEntitySpecification(new SearchCriteria("lastName", ":", "Therieur"));
        UserEntitySpecification spec2 =
                new UserEntitySpecification(new SearchCriteria("age", ">", "24"));

        //combine several specifications : Specification.where(spec1).and(spec2)
        List<UserEntity> results = repository.findAll(Specification.where(spec1).and(spec2));
        assertTrue(results.size()>=1);
        assertThat(results).usingRecursiveFieldByFieldElementComparator().contains(userAlain).doesNotContain(userAlex);
        log.debug("users with specif 'firstName : Therieur' AND 'age > 24' : " + results);

    }

}
