package tp.mySpringBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.mySpringBoot.entity.Compte;

import java.util.List;
import java.util.Optional;
//interface de composant spring d'accès aux données
//avec des méthodes CRUD : Create , Retreive , Update , Delete

public interface RepositoryCompte extends JpaRepository<Compte,Long> {

    List<Compte> findByLabelLike(String  label);

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

