package tp.appliSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tp.appliSpring.entity.CompteEntity;

import java.util.List;

public interface CompteRepository extends JpaRepository<CompteEntity,Long> {
    //principales méthodes héritées : .findAll() , .findById() , .save() , .deleteById()

    @Query("SELECT c FROM CompteEntity c WHERE c.solde >  :soldeMini")
    List<CompteEntity> rechercherCompteAvecSoldeMini(double soldeMini);

    //via le mot clef FETCH les operations du compte remontent tout de suite en mémoire (comme avec EAGER)
    @Query("SELECT c FROM CompteEntity c LEFT JOIN FETCH c.operations WHERE c.numero = :numCompte")
    CompteEntity rechercherCompteAvecOperations(long numCompte);

    //idem avec respect des conventions de noms de méthode
    List<CompteEntity> findBySoldeGreaterThanEqual(double soldeMini);
}
