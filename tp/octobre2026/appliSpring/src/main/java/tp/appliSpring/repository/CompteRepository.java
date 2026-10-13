package tp.appliSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tp.appliSpring.entity.CompteEntity;

import java.util.List;

public interface CompteRepository extends JpaRepository<CompteEntity,Long> {
    //principales méthodes héritées : .findAll() , .findById() , .save() , .deleteById()

    @Query("SELECT c FROM CompteEntity c WHERE c.solde >  :soldeMini")
    List<CompteEntity> rechercherCompteAvecSoldeMini(double soldeMini);

    //idem avec respect des conventions de noms de méthode
    List<CompteEntity> findBySoldeGreaterThanEqual(double soldeMini);
}
