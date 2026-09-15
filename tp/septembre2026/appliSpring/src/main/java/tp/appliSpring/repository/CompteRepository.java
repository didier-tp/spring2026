package tp.appliSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tp.appliSpring.entity.CompteEntity;

import java.util.List;

public interface CompteRepository extends JpaRepository<CompteEntity,Long> {
    //principales méthodes héritées: .save() , .findById() , .findAll() , .deleteById();


    //avec un nom de méthode respectant des conventions de nommage, le SQL sera généré automatiquement
    List<CompteEntity> findBySoldeGreaterThanEqual(double soldeMini);

    //avec un nom de méthode respectant pas des conventions de nommage, le sql version JPA peut être précisé par @Query
    @Query("SELECT c FROM CompteEntity c WHERE c.solde >= :soldeMini")
    List<CompteEntity> findBySoldeMini(double soldeMini);
}
