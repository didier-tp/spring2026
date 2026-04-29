package tp.mySpringBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tp.mySpringBoot.entity.Compte;

import java.util.List;
import java.util.Optional;
//interface de composant spring d'accès aux données
//avec des méthodes CRUD : Create , Retreive , Update , Delete

public interface RepositoryCompte extends JpaRepository<Compte,Long> {
    /* principales méthodes héritées:
       .save() , .findById() , .deleteById()
    */
    @Query("SELECT c FROM Compte c LEFT JOIN FETCH c.operations op WHERE c.numero = :numCompte")
    Optional<Compte> findCompteWithOperationsById(Long numCompte);

    List<Compte> findByLabelLike(String  label);

    List<Compte> findBySoldeBetween(double soldeMini,double soldeMaxi);

    @Query("SELECT c FROM Compte c WHERE c.solde>= :soldeMini AND c.solde<= :soldeMaxi")
    List<Compte> rechercherAvecSoldeEntre(double soldeMini,double soldeMaxi);

    //...
}

/*
//VERSION sans Spring data
public interface RepositoryCompte {
    Optional<Compte> findById(Long num);
    List<Compte> findByLabel(String  label);
    List<Compte> findAll();
    Compte save(Compte compte); //Create(insert into) or Update
    void deleteById(Long num);
    //...
}
*/

