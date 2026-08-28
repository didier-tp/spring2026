package tp.appliSpring.bank.persistence.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;
import tp.appliSpring.AppliSpringApplication;
import tp.appliSpring.bank.persistence.criteria.SearchCriteria;
import tp.appliSpring.bank.persistence.entity.UserEntity;
import tp.appliSpring.bank.persistence.specif.UserEntitySpecification;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes= {AppliSpringApplication.class})//reprendre la configuration de la classe principale
@ActiveProfiles({  "dev" })
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired) //pour injection de dépendances via constructeur (final fields)
public class TestUserRepositoryWithPagingAndSorting {

    private final UserRepository repository;

    @BeforeEach
    public void initUserDataSet() {
        repository.save(new UserEntity(null,"Alex","Therieur","alex.therieur@xyz.com",22));
        repository.save(new UserEntity(null,"Alain","Therieur","alain.therieur@xyz.com",26));
        repository.save(new UserEntity(null,"Axelle","Aire","axelle.aire@xyz.com",28));
        repository.save(new UserEntity(null,"Jean","Bon","jean.bon@xyz.com",22));
        repository.save(new UserEntity(null,"Jean","Aimare","jean.aimare@xyz.com",27));
        repository.save(new UserEntity(null,"Olie","Condor","olie.condor@xyz.com",32));
        repository.save(new UserEntity(null,"Remi","Fasol","remi.fasol@xyz.com",26));
        repository.save(new UserEntity(null,"Paul","Ochon","paul.ochon@xyz.com",28));
        repository.save(new UserEntity(null,"Jean","Peuplu","jean.peuplu@xyz.com",22));
        repository.save(new UserEntity(null,"Laurent","Houtan","laurent.houtan@xyz.com",27));
        repository.save(new UserEntity(null,"Tristan","Douille","tristan.douille@xyz.com",27));
        repository.save(new UserEntity(null,"Marc","Assin","marc.assin@xyz.com",37));
        repository.save(new UserEntity(null,"John","Doeuf","john.doeuf@xyz.com",37));
        repository.save(new UserEntity(null,"Samira","Bien","samira.bien@xyz.com",37));
        repository.save(new UserEntity(null,"Sarah","Courcie","sarah.courcie@xyz.com",37));
        repository.save(new UserEntity(null,"Aude","Javel","aude.javel@xyz.com",37));
        repository.save(new UserEntity(null,"Cecile","Encieux","cecile.encieux@xyz.com",37));
        repository.save(new UserEntity(null,"Léa","Ricossec","lea.ricossec@xyz.com",37));
        repository.save(new UserEntity(null,"Laram","Masse","cecile.encieux@xyz.com",37));
        repository.save(new UserEntity(null,"Daisy","Drate","daisy.drate@xyz.com",37));
        repository.save(new UserEntity(null,"Edith","Orial","edith.orial@xyz.com",37));
        //21 ou plus
    }

    @Test
    public void testWithSorting(){
        List<UserEntity> results = repository.findAll(Sort.by("firstName").descending());
        assertTrue(results.size()>=21);
        log.debug("users sort by firstName descending : " + results);

    }

    @Test
    public void testWithPaging(){
        assertEquals(21, repository.count());
        Page<UserEntity> pageDePersonnes =
                // 1eme page (at index 0) de résultats et 5 résultats max.
                repository.findAll(PageRequest.of(0, 5)); //page index: 0,1,n-1
        assertEquals(0, pageDePersonnes.getNumber());
        assertEquals(5, pageDePersonnes.getSize());// la taille d'une page
        assertEquals(21, pageDePersonnes.getTotalElements());
        assertEquals(5, pageDePersonnes.getTotalPages());
        assertTrue(pageDePersonnes.hasContent());
        List<UserEntity> results =pageDePersonnes.getContent();
        log.debug("first page (at index 0) of users  : " + results);


    }


}
